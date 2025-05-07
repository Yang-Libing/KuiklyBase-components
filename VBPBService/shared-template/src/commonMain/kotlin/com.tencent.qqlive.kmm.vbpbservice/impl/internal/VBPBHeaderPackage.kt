package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.ditchoom.buffer.PlatformBuffer
import com.ditchoom.buffer.allocate
import com.ditchoom.buffer.wrap
import com.squareup.wire.kmm.Message
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBAutoRetryFlag
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResultCode
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResultCodeType
import com.tencent.qqlive.protocol.vb.pb.kmm.ClientReportInfo
import com.tencent.qqlive.protocol.vb.pb.kmm.RequestHead
import com.tencent.qqlive.protocol.vb.pb.kmm.ResponseHead
import com.tencent.trpc.proto.standard.common.vbpb.kmm.ResponseProtocol
import okio.ByteString

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB请求公参打包/解包实现
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
class VBPBHeaderPackage<RERQUEST : Message<*, *>>(
    private val logTag: String
) {
    private lateinit var commonRequestHead: RequestHead

    // 拼接公参和业务数据字节数组
    fun packageHeaderAndBody(headerBytes: ByteArray, bodyBytes: ByteArray): ByteArray {
        logI("package trpc header size:${headerBytes.size}, body size ${bodyBytes.size}")
        val totalLen: Int = headerBytes.size + bodyBytes.size
        val buffer: PlatformBuffer = PlatformBuffer.allocate(totalLen)
        buffer.writeBytes(headerBytes)
        buffer.writeBytes(bodyBytes)
        buffer.position(0)
        return buffer.readByteArray(totalLen)
    }

    // 获取请求公参对象
    fun getRequestHead(): RequestHead = commonRequestHead

    // 公参打包为字节数组
    fun packageCommonHead(
        requestId: Int,
        callee: String,
        func: String,
        extraMap: Map<String?, String?>,
        autoRetryFlag: VBPBAutoRetryFlag,
    ): PlatformBuffer {
        logI("packageCommonHead")
        val headerConfig = VBPBHeaderConfig()

        // 请求唯一ID
        val uniqueId = headerConfig.createUniqueId(requestId)
        // 基础公参
        commonRequestHead =
            headerConfig.createCommonRequestHead(requestId, logTag, callee, func, uniqueId)
        logI("createCommonRequestHead finish")
        // 添加自动重试标记
        val flagInfo = commonRequestHead.flag_info?.copy(auto_retry_flag = autoRetryFlag.value)
        // 自定义header数据
        val busiExtraHeadMap = filterNullInMap(extraMap)
        val extraHead: MutableMap<String, String> = mutableMapOf()
        val commonExtraHead: Map<String, String> = commonRequestHead.extra_request_head
        if (isHeaderMapNotEmpty(commonExtraHead)) {
            extraHead.putAll(commonExtraHead)
        }
        extraHead.putAll(busiExtraHeadMap)

        // 补充公参
        commonRequestHead = commonRequestHead.copy(
            client_report_info = createClientReportInfo(),
            request_id = requestId,
            callee = callee,
            func = func,
            extra_request_head = extraHead,
            unique_id = uniqueId,
            flag_info = flagInfo
        )

        logI("package common header:$commonRequestHead")
        return PlatformBuffer.wrap(commonRequestHead.encode())
    }

    private fun isHeaderMapNotEmpty(headerMap: Map<String, String>?): Boolean =
        !headerMap.isNullOrEmpty()

    private fun filterNullInMap(map: Map<String?, String?>): Map<String, String> {
        val result: MutableMap<String, String> = mutableMapOf()
        for ((key, value) in map) {
            if (key != null && value != null) {
                result[key] = value
            }
        }
        return result
    }

    fun createClientReportInfo(): ClientReportInfo =
        ClientReportInfo(client_send_timestamp = getTimestamp())

    // 去除请求头信息
    fun unpackageHeader(pbDataBytes: ByteArray, headLen: Int, bodyLen: Int): VBPBUnPackageResult {
        logI("unpackage trpc header start,total len :${pbDataBytes.size}, head len:$headLen, body len:$bodyLen")
        val unPackageResult = VBPBUnPackageResult()
        val unpackageDataBuffer = PlatformBuffer.wrap(pbDataBytes)
        val headBytes = unpackageDataBuffer.readByteArray(headLen)
        // 反序列化 ResponseHeader
        val trpcHeader: ResponseProtocol?
        try {
            trpcHeader = ResponseProtocol.ADAPTER.decode(headBytes)
            unPackageResult.transInfo = mutableMapOf<String, ByteString>().apply {
                trpcHeader.trans_info.forEach { (key, value) ->
                    this[key] = value
                }
            }
        } catch (e: Throwable) {
            logI("unpackage trpc header fail, protocol decode is incorrect")
            unPackageResult.errorCode = VBPBResultCode.ERR_RESPONSE_PARSE_EXCEPTION
            unPackageResult.errorCodeType = VBPBResultCodeType.ERROR_CODE_TYPE_TRPC_PACK_ERR
            return unPackageResult
        }
        // TODO 限流策略

        // 解析视频header
        val videoHead = trpcHeader.trans_info[VBPBPackage.QQLIVE_HEAD_RESPONSE]
        val parseVideoHeadResult = unpackageVideoHead(unPackageResult, videoHead)
        if (!parseVideoHeadResult) {
            return unPackageResult
        }
        // 校验请求id
        val checkRequestIdResult = checkRequestId(unPackageResult, trpcHeader)
        if (!checkRequestIdResult) {
            logI("unpackage trpc header fail, request id is null")
            return unPackageResult
        }
        logI("unpackage trpc header ret:${trpcHeader.ret}, func_ret:${trpcHeader.func_ret}")

        // 解析 header 结果码，从 trpchead中 取，video head中结果码废弃
        unpackageTrpcHeadCode(unPackageResult, trpcHeader)

        if (headLen + bodyLen > unpackageDataBuffer.capacity) {
            logI("unpackage trpc header fail head len:${headLen}, body len:{$bodyLen}, raw bytes len:${unpackageDataBuffer.capacity}")
            unPackageResult.errorCode = VBPBResultCode.CODE_PB_ERR_BODY
            unPackageResult.errorCodeType = VBPBResultCodeType.ERROR_CODE_TYPE_TRPC_PACK_ERR
            return unPackageResult
        }
        unPackageResult.messageBytes = unpackageDataBuffer.readByteArray(bodyLen)
        logI("unpackage trpc header done")
        return unPackageResult
    }

    // requestid 校验
    private fun checkRequestId(
        unPackageResult: VBPBUnPackageResult,
        trpcHead: ResponseProtocol
    ): Boolean {
        return if (trpcHead.request_id == null) {
            unPackageResult.errorCode = VBPBResultCode.ERR_RESPONSE_REQUEST_ID_NULL
            unPackageResult.errorCodeType = VBPBResultCodeType.ERROR_CODE_TYPE_TRPC_PACK_ERR
            VBPBLog.e(VBPBLog.PACKAGE_IMPL, "unpackageTrpcHeader request_id null")
            return false
        } else true
    }

    // 解析Trpc头中的结果码
    private fun unpackageTrpcHeadCode(
        unPackageResult: VBPBUnPackageResult,
        trpcHeader: ResponseProtocol
    ) {
        if (trpcHeader.ret !== 0) {
            unPackageResult.errorCode = trpcHeader.ret
            unPackageResult.errorCodeType = VBPBResultCodeType.ERROR_CODE_TYPE_ACCESS_SVR_ERR
        } else if (trpcHeader.func_ret !== 0) {
            val businessCode: Int = trpcHeader.func_ret
            unPackageResult.errorCode = businessCode
            unPackageResult.businessCode = businessCode
            unPackageResult.errorCodeType = VBPBResultCodeType.ERROR_CODE_TYPE_BUSI_RS_ERR
        }
        unPackageResult.errorMessage = trpcHeader.error_msg.toString()
    }

    // 解析视频响应头信息
    private fun unpackageVideoHead(
        unPackageResult: VBPBUnPackageResult,
        videoRspHead: ByteString?
    ): Boolean {
        if (videoRspHead == null || videoRspHead.size == 0) {
            logI("unpackage common header fail, common head data is empty")
            return true
        }
        return try {
            val responseHead: ResponseHead = ResponseHead.ADAPTER.decode(videoRspHead)
            unPackageResult.responseHead = responseHead
            unPackageResult.needRetryFlag = isNeedRetryFlag(responseHead) // 是否可以重试标记
            true
        } catch (e: Throwable) {
            logI("unpackage common header fail, protocol decode is incorrect")
            unPackageResult.errorCode = VBPBResultCode.ERR_RESPONSE_PARSE_EXCEPTION
            unPackageResult.errorCodeType = VBPBResultCodeType.ERROR_CODE_TYPE_TRPC_PACK_ERR
            unPackageResult.errorMessage = e.message.toString()
            false
        }
    }

    // 是否需要重试
    private fun isNeedRetryFlag(responseHead: ResponseHead?): Boolean =
        responseHead?.flag_info?.need_retry_flag == 1

    private fun logI(content: String) {
        VBPBLog.i(VBPBLog.HEADER_PACKAGE, "$logTag $content")
    }
}