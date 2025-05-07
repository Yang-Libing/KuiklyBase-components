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

// Curl iOS 平台实现
object IOSCurlRequestService : ICurlRequestService {
    override fun get(
        kmmGetRequest: VBTransportGetRequest,
        kmmGetResponseCallback: (response: VBTransportGetResponse, transportReportInfo: VBTransportReportInfo?) -> Unit,
        logTag: String
    ) {
        TODO("Not yet implemented")
    }

    override fun post(
        kmmPostRequest: VBTransportPostRequest,
        kmmPostResponseCallback: (response: VBTransportPostResponse, transportReportInfo: VBTransportReportInfo?) -> Unit,
        logTag: String
    ) {
//        TODO("Not yet implemented")
    }

    override fun sendStringRequest(
        kmmStringRequest: VBTransportStringRequest,
        kmmStringResponseCallback: (response: VBTransportStringResponse, transportReportInfo: VBTransportReportInfo?) -> Unit,
        logTag: String
    ) {
//        TODO("Not yet implemented")
    }

    override fun sendBytesRequest(
        kmmBytesRequest: VBTransportBytesRequest,
        kmmBytesResponseCallback: (response: VBTransportBytesResponse, transportReportInfo: VBTransportReportInfo?) -> Unit,
        logTag: String
    ) {
//        TODO("Not yet implemented")
    }

    override fun <REQUEST : Message<*, *>, RESPONSE : Message<*, *>> sendPBRequest(
        kmmPBRequest: VBPBRequest<REQUEST, RESPONSE>,
        kmmPBResponseCallback: (response: VBPBResponse<RESPONSE>, transportReportInfo: VBTransportReportInfo?) -> Unit,
        logTag: String
    ) {
//        TODO("Not yet implemented")
    }

    override fun cancel(requestId: Int) {
//        TODO("Not yet implemented")
    }

    override fun initNativeCurlLog(log: IVBPBLog) {
//        TODO("Not yet implemented")
    }
}

actual fun getCurlRequestService(): ICurlRequestService = IOSCurlRequestService