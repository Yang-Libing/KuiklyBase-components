package com.tencent.qqlive.kmm.vbpbservice.impl.platform

import com.squareup.wire.kmm.Message
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequest
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResponse
import com.tencent.qqlive.kmm.vbpbservice.export.VBTransportReportInfo
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBytesRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBytesResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportGetRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportGetResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportPostRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportPostResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportStringRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportStringResponse

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * 跨端传输服务接口
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
interface IVBTransportService {

    /**
     * 发送请求
     *
     * @param kmmPBRequest kmm层封装的请求信息
     * @param kmmPBResponseCallback kmm 层响应回调函数
     */
    fun <REQUEST : Message<*, *>, RESPONSE : Message<*, *>> sendPBRequest(
        kmmPBRequest: VBPBRequest<REQUEST, RESPONSE>,
        kmmPBResponseCallback:
            (
            response: VBPBResponse<RESPONSE>,
            transportReportInfo: VBTransportReportInfo?
                    ) -> Unit,
    )

    /**
     * 发送字节数组类型请求
     */
    fun sendBytesRequest(
        kmmBytesRequest: VBTransportBytesRequest,
        kmmBytesResponseCallback:
            (
            response: VBTransportBytesResponse,
            transportReportInfo: VBTransportReportInfo?
                    ) -> Unit,
    )

    /**
     * 发送字节数组类型请求
     */
    fun sendStringRequest(
        kmmStringRequest: VBTransportStringRequest,
        kmmStringResponseCallback:
            (
            response: VBTransportStringResponse,
            transportReportInfo: VBTransportReportInfo?
                    ) -> Unit,
    )

    /**
     * 发送POST请求
     */
    fun post(
        kmmPostRequest: VBTransportPostRequest,
        kmmPostResponseCallback:
            (
            response: VBTransportPostResponse,
            transportReportInfo: VBTransportReportInfo?
                    ) -> Unit,
    )

    /**
     * 发送GET请求
     */
    fun get(
        kmmGetRequest: VBTransportGetRequest,
        kmmGetResponseCallback:
            (
            response: VBTransportGetResponse,
            transportReportInfo: VBTransportReportInfo?
                    ) -> Unit,
    )

    /**
     * 设置日志代理
     */
    fun setLogImpl(logImpl: (tag: String, content: String) -> Unit)

    /**
     * 取消网络请求
     */
    fun cancel(requestId: Int, useCurl: Boolean)

    /**
     * 获取网络类型
     */
    fun getNetworkType(): Int

}

// 需要各平台实现获取传输能力的实力
expect fun getIVBTransportService(): IVBTransportService