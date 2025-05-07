package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.squareup.wire.kmm.Message
import com.squareup.wire.kmm.ProtoAdapter
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBAutoRetryFlag
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequestInfo
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResultCode
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResultCodeType
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBTransInfoManager
import com.tencent.trpc.proto.standard.common.vbpb.kmm.RequestProtocol
import okio.ByteString
import okio.ByteString.Companion.encodeUtf8
import okio.ByteString.Companion.toByteString

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 据结协议打包/解包实现
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
class VBPBPackage<REQUEST : Message<*, *>, RESPONSE : Message<*, *>>(
    private val responseAdapter: ProtoAdapter<RESPONSE>,
    private val logTag: String,
) {
    lateinit var requestMessage: REQUEST
    lateinit var headerPackage: VBPBHeaderPackage<REQUEST>
    lateinit var messagePackage: VBPBMessagePackage<REQUEST, RESPONSE>
    lateinit var pbFramePackage: VBPBFramePackage
    private var headerLen: Int = 0
    private var requestId: Int = 0
    lateinit var callee: String
    lateinit var func: String
    lateinit var serviceTag: String

    /**
     * 请求打包
     *
     * @param requestId 请求id
     * @param requestMessage 请求的PBMessage对象
     * @param callee 请求callee路由信息
     * @param func 请求func路由信息
     * @param serviceTag 场景信息
     * @param extraMap 附加参数Map
     * @return 打包结果
     */
    fun packageRequest(
        requestId: Int,
        requestMessage: REQUEST,
        callee: String,
        func: String,
        serviceTag: String,
        extraMap: Map<String?, String?>,
        autoRetryFlag: VBPBAutoRetryFlag,
        gzipEnabled: Boolean,
    ): VBPBPackageMessageResult {
        val packageStartTs = getTimestamp()
        logI("packageRequest() start, callee:$callee, func:$func, serviceTag:$serviceTag")
        this.requestMessage = requestMessage
        this.requestId = requestId
        this.callee = callee
        this.serviceTag = serviceTag
        this.func = func
        messagePackage = VBPBMessagePackage(responseAdapter, logTag)
        headerPackage = VBPBHeaderPackage(logTag)
        pbFramePackage = VBPBFramePackage(logTag)
        val packageMessageResult = VBPBPackageMessageResult()

        // 1.PB Message --> byte[]
        val busiRequestBytes: ByteArray = packageMessageDataBytes(requestMessage)

        // 2.加Header
        val rawBytes: ByteArray =
            packageHeader(callee, func, extraMap, busiRequestBytes, autoRetryFlag)

        // 3.加PB Frame
        val pbPackageBytes: ByteArray = packagePBFrame(rawBytes)

        // 4. GZIP
        if (gzipEnabled) {
            val zippedPackageBytes: ByteArray = GzipUtils.gzipCompress(pbPackageBytes)
            packageMessageResult.packageBytes = zippedPackageBytes
            logI("gzip before size: ${pbPackageBytes.size}, after size: ${zippedPackageBytes.size}")
        } else {
            packageMessageResult.packageBytes = pbPackageBytes
        }

        packageMessageResult.requestHead = headerPackage.getRequestHead()

        VBPBReportManager.setPackageTimeSpent(requestId, packageStartTs)
        logI("packageRequest() done")
        return packageMessageResult
    }


    /**
     * 响应解包
     *
     * @param responseBytes 原始响应字节数组
     * @return 解包结果
     */
    fun unpackageResponse(responseBytes: ByteArray?): VBPBUnPackageMessageResult<RESPONSE> {
        val unpackageStartTs = getTimestamp()
        logI("unpackageResponse() start, response bytes size:${responseBytes?.size}")
        val unpackageRpesult: VBPBUnPackageMessageResult<RESPONSE> = VBPBUnPackageMessageResult()

        // 响应数据包空校验
        if (responseBytes == null || responseBytes.isEmpty()) {
            logI("unpackageResponse() fail, response bytes is empty")
            setUnpackageResult(
                unpackageRpesult,
                VBPBResultCodeType.ERROR_CODE_TYPE_ACCESS_SVR_ERR,
                VBPBResultCode.CODE_HTTP_ENTITYNULL,
                "响应数据包为空"
            )
            return unpackageRpesult
        }

        // 1.拆解 PB协议 及结果校验
        val unpackagePBResult: VBPBUnPackageResult = unpackagePBFrame(responseBytes)
        if (unpackagePBResult.errorCode != VBPBResultCode.CODE_OK) {
            logI("unpackageResponse() fail, unpackage pb frame fail")
            setUnpackageResult(
                unpackageRpesult,
                VBPBResultCodeType.ERROR_CODE_TYPE_ACCESS_SVR_ERR,
                VBPBResultCode.CODE_HTTP_ENTITYNULL,
                "响应数据包为空"
            )
            return unpackageRpesult
        }

        // 2.拆解 业务数据协议 及结果校验
        val unpackagePBData: ByteArray? = unpackagePBResult.messageBytes
        if (unpackagePBData == null || unpackagePBData.isEmpty()) {
            logI("unpackageResponse() fail, pb message data is empty")
            setUnpackageResult(
                unpackageRpesult,
                VBPBResultCodeType.ERROR_CODE_TYPE_FRAME_PACK_ERR,
                VBPBResultCode.CODE_PB_ERR_BODY,
                "PB协议解除后数据为空"
            )
            return unpackageRpesult
        }

        // 3. Trpc协议头+业务数据 解包 及结果校验
        val busiUnpackageResult: VBPBUnPackageResult = unpackageHeader(unpackagePBData)
        unpackageRpesult.transInfo = busiUnpackageResult.transInfo
        unpackageRpesult.responseHead = busiUnpackageResult.responseHead
        unpackageRpesult.needRetryFlag = busiUnpackageResult.needRetryFlag
        val messageBytes = busiUnpackageResult.messageBytes

        val resultCode = busiUnpackageResult.errorCode
        if (resultCode != VBPBResultCode.CODE_OK) {
            logI("unpackageResponse() fail, unpackage error code:$resultCode")
            unpackageRpesult.errorCode = resultCode
            unpackageRpesult.errorMessage = busiUnpackageResult.errorMessage
            unpackageRpesult.errorCodeType = busiUnpackageResult.errorCodeType
            // 返回错误时，也解析业务body
            getErrorPackageMessage(unpackageRpesult, messageBytes)
            return unpackageRpesult
        }
        // 4. byte[] 转 PB Message
        val unpackageResult = getUnpackageMessageResult(unpackageRpesult, messageBytes)
        VBPBReportManager.setUnpackageTimeSpent(requestId, unpackageStartTs)

        // 5. 更新公参
        val responseHead = busiUnpackageResult.responseHead
        responseHead?.refresh_token_response_v2?.let {
            VBPBConfig.setRefreshTokenResponse(it)
        }
        responseHead?.refresh_response?.let {
            VBPBConfig.setRefreshResponse(it)
        }
        responseHead?.user_status_info?.let {
            VBPBConfig.setUserStatusInfo(it)
        }

        // 自定义处理响应的transinfo信息
        val requestInfo = VBPBRequestInfo()
        requestInfo.func = func;
        requestInfo.callee = callee;
        VBPBTransInfoManager.onRspTransInfo(requestInfo, busiUnpackageResult.transInfo)
        return unpackageResult;
    }

    // 业务数据打包
    private fun packageMessageDataBytes(requestMessage: REQUEST): ByteArray =
        messagePackage.packageMessage(requestMessage)

    // 公参打包
    private fun packageHeader(
        callee: String,
        func: String,
        extraMap: Map<String?, String?>,
        busiRequestBytes: ByteArray,
        autoRetryFlag: VBPBAutoRetryFlag,
    ): ByteArray {
        logI("packageHeader")
        val packageHeaderStartTs = getTimestamp()
        val headerBytes: ByteArray = packageTrpcHead(callee, func, extraMap, autoRetryFlag)
        headerLen = headerBytes.size
        val headerAndBodyBytes: ByteArray =
            headerPackage.packageHeaderAndBody(headerBytes, busiRequestBytes)
        VBPBReportManager.setPackageHeaderTimeSpent(requestId, packageHeaderStartTs)
        return headerAndBodyBytes
    }

    // PB协议帧打包
    private fun packagePBFrame(rawBytes: ByteArray): ByteArray {
        val packagePBFrameStartTs = getTimestamp()
        val dataWithPBFrame: ByteArray =
            pbFramePackage.packagePBRequest(headerLen.toShort(), rawBytes)
        VBPBReportManager.setPackagePBFrameTimeSpent(requestId, packagePBFrameStartTs)
        return dataWithPBFrame
    }

    // 根据返回字节数组获取响应PBMessage对象
    private fun getUnpackageMessageResult(
        unpackageResult: VBPBUnPackageMessageResult<RESPONSE>,
        unpackageBusiData: ByteArray?,
    ): VBPBUnPackageMessageResult<RESPONSE> {
        val messageUnpackageResult = unpackageMessage(unpackageBusiData)
        val resultCode = messageUnpackageResult.errorCode
        if (resultCode != VBPBResultCode.ERR_RESPONSE_PARSE_DATA_EMPTY && resultCode != VBPBResultCode.CODE_OK) {
            logI("getUnpackageMessageResult() fail, error code:$resultCode")
            setUnpackageResult(
                unpackageResult,
                VBPBResultCodeType.ERROR_CODE_TYPE_BUSI_PACK_ERR,
                resultCode,
                "响应数据转Message失败"
            )
            return unpackageResult
        }
        unpackageResult.errorCode = VBPBResultCode.CODE_OK
        unpackageResult.responseMessage = messageUnpackageResult.responseMessage
        return unpackageResult
    }

    // 拼装TrpcHead公参信息
    private fun packageTrpcHead(
        callee: String,
        func: String,
        extraMap: Map<String?, String?>,
        autoRetryFlag: VBPBAutoRetryFlag
    ): ByteArray {
        logI("packageTrpcHead")
        // TODO 对接tony公参部分
        val videoHeaderBytes =
            headerPackage.packageCommonHead(requestId, callee, func, extraMap, autoRetryFlag);

        // 公参转trans_info
        val videoHeader = videoHeaderBytes.readByteArray(videoHeaderBytes.capacity).toByteString()
        val transInfo: MutableMap<String, ByteString> =
            mutableMapOf(QQLIVE_HEAD_REQUEST to videoHeader)

        // 自定义处理请求的transinfo信息
        val requestInfo = VBPBRequestInfo()
        requestInfo.func = func;
        requestInfo.callee = callee;
        VBPBTransInfoManager.onReqTransInfo(requestInfo, transInfo)

        // 拼接TrpcHeader
        val trpcHead = RequestProtocol(
            request_id = requestId,
            callee = callee.encodeUtf8(),
            func = func.encodeUtf8(),
            trans_info = transInfo
        )
        return trpcHead.encode()
    }

    // 解包失败后解析PBMessage对象
    private fun getErrorPackageMessage(
        unPackageResult: VBPBUnPackageMessageResult<RESPONSE>,
        responseBytes: ByteArray?,
    ) {
        unPackageResult.responseMessage = unpackageMessage(responseBytes).responseMessage
    }

    // 根据业务的字节数组转换为PBMessage对象
    private fun unpackageMessage(
        unpackageBusiData: ByteArray?
    ): VBPBUnPackageMessageResult<RESPONSE> = messagePackage.unpackageMessage(unpackageBusiData)

    // 根据响应的字节数组解析响应公参信息
    private fun unpackageHeader(unpackagePBData: ByteArray): VBPBUnPackageResult {
        val unpackageHeaderStartTs = getTimestamp()
        val headLen: Int = pbFramePackage.responseHeadLen
        val bodyLen: Int = pbFramePackage.responseBodyLen
        val unPackageResult: VBPBUnPackageResult =
            headerPackage.unpackageHeader(unpackagePBData, headLen, bodyLen)
        VBPBReportManager.setUnpackageHeaderTimeSpent(requestId, unpackageHeaderStartTs)
        return unPackageResult
    }

    // 根据响应的字节数组移除PB帧协议
    private fun unpackagePBFrame(byteArray: ByteArray): VBPBUnPackageResult {
        val unpackagePBFrameStartTs = getTimestamp()
        val unpackageResult = pbFramePackage.unpackagePBResponse(byteArray)
        VBPBReportManager.setUnpackagePBFrameTimeSpent(requestId, unpackagePBFrameStartTs)
        return unpackageResult
    }

    // 设置解包结果
    private fun setUnpackageResult(
        unPackageResult: VBPBUnPackageMessageResult<RESPONSE>,
        errorType: String,
        resultCode: Int,
        message: String
    ) {
        unPackageResult.errorCode = resultCode
        unPackageResult.errorMessage = message
        unPackageResult.errorCodeType = errorType
    }

    private fun logI(content: String) {
        VBPBLog.i(VBPBLog.PACKAGE_IMPL, "$logTag $content")
    }

    companion object {
        val QQLIVE_HEAD_REQUEST = "qqlive_head"
        val QQLIVE_HEAD_RESPONSE = "qqlive_rsp_head"
        val QQLIVE_HEAD_COMPRESS_KEY = "qqlive_req_head_encoding"
        val QQLIVE_HEAD_BLOCK_INTERVAL = "vb_block_interval"
    }
}