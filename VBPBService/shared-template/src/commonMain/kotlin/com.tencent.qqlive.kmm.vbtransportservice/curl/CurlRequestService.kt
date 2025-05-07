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
 * curl 请求管理类
 *
 * @author berryyang
 * @date 2025/2/26 11:04
 */
object CurlRequestService {
    fun sendGetRequest(
        request: VBTransportGetRequest,
        handler: (response: VBTransportGetResponse, transportReportInfo: VBTransportReportInfo?) -> Unit,
        logTag: String = "CurlRequestService"
    ) {
        getCurlRequestService().get(request, handler, logTag)
    }

    fun sendPostRequest(
        request: VBTransportPostRequest,
        handler: (response: VBTransportPostResponse, transportReportInfo: VBTransportReportInfo?) -> Unit,
        logTag: String = "CurlRequestService"
    ) {
        getCurlRequestService().post(request, handler, logTag)
    }

    fun sendStringRequest(
        request: VBTransportStringRequest,
        handler: ((response: VBTransportStringResponse, transportReportInfo: VBTransportReportInfo?) -> Unit),
        logTag: String
    ) {
        getCurlRequestService().sendStringRequest(request, handler, logTag)
    }

    fun sendBytesRequest(
        request: VBTransportBytesRequest,
        handler: ((response: VBTransportBytesResponse, transportReportInfo: VBTransportReportInfo?) -> Unit),
        logTag: String
    ) {
        getCurlRequestService().sendBytesRequest(request, handler, logTag)
    }

    fun <REQUEST : Message<*, *>, RESPONSE : Message<*, *>> sendPBRequest(
        kmmPBRequest: VBPBRequest<REQUEST, RESPONSE>,
        kmmPBResponseCallback: (response: VBPBResponse<RESPONSE>, transportReportInfo: VBTransportReportInfo?) -> Unit,
        logTag: String
    ) {
        getCurlRequestService().sendPBRequest(kmmPBRequest, kmmPBResponseCallback, logTag)
    }

    fun cancel(requestId: Int) {
        getCurlRequestService().cancel(requestId)
    }

    /**
     * libcurl 日志初始化接口
     */
    fun initNativeCurlLog(log: IVBPBLog) {
        getCurlRequestService().initNativeCurlLog(log)
    }
}

expect fun getCurlRequestService(): ICurlRequestService