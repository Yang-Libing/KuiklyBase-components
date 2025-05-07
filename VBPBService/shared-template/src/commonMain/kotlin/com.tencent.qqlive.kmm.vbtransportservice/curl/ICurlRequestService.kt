package com.tencent.qqlive.kmm.vbtransportservice.curl

import com.squareup.wire.kmm.Message
import com.tencent.qqlive.kmm.vbpbservice.export.IVBPBLog
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
import kotlinx.coroutines.Job

/**
 * Copyright (c) 2025 Tencent. All rights reserved
 *
 * curl 请求接口类
 *
 * @author berryyang
 * @date 2025/2/26 14:11
 */
interface ICurlRequestService {
    /**
     * 发送 GET 请求
     */
    fun get(
        kmmGetRequest: VBTransportGetRequest,
        kmmGetResponseCallback: (
            response: VBTransportGetResponse, transportReportInfo: VBTransportReportInfo?
                ) -> Unit,
        logTag: String
    )

    /**
     * 发送 POST 请求
     */
    fun post(
        kmmPostRequest: VBTransportPostRequest,
        kmmPostResponseCallback: (
            response: VBTransportPostResponse, transportReportInfo: VBTransportReportInfo?
                ) -> Unit,
        logTag: String
    )

    /**
     * 发送字符串类型请求
     */
    fun sendStringRequest(
        kmmStringRequest: VBTransportStringRequest,
        kmmStringResponseCallback: (
            response: VBTransportStringResponse, transportReportInfo: VBTransportReportInfo?
                ) -> Unit,
        logTag: String
    )

    /**
     * 发送字节数组类型请求
     */
    fun sendBytesRequest(
        kmmBytesRequest: VBTransportBytesRequest,
        kmmBytesResponseCallback: (
            response: VBTransportBytesResponse, transportReportInfo: VBTransportReportInfo?
                ) -> Unit,
        logTag: String
    )

    /**
     * 发送 PB 请求
     *
     * @param kmmPBRequest kmm层封装的请求信息
     * @param kmmPBResponseCallback kmm 层响应回调函数
     */
    fun <REQUEST : Message<*, *>, RESPONSE : Message<*, *>> sendPBRequest(
        kmmPBRequest: VBPBRequest<REQUEST, RESPONSE>,
        kmmPBResponseCallback: (
            response: VBPBResponse<RESPONSE>, transportReportInfo: VBTransportReportInfo?
                ) -> Unit,
        logTag: String
    )

    /**
     * 取消请求
     *
     * @param requestId 请求id
     */
    fun cancel(requestId: Int)

    /**
     * libcurl 日志初始化接口
     */
    fun initNativeCurlLog(log: IVBPBLog)
}