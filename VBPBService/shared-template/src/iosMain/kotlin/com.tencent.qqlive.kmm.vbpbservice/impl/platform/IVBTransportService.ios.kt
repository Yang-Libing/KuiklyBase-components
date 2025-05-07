package com.tencent.qqlive.kmm.vbpbservice.impl.platform

import com.squareup.wire.kmm.Message
import com.tencent.kmm.transportservice.TransportHTTPContentType
import com.tencent.kmm.transportservice.TransportHTTPMethod
import com.tencent.kmm.transportservice.TransportHTTPProtocolStrategy
import com.tencent.kmm.transportservice.TransportMetrics
import com.tencent.kmm.transportservice.TransportRequest
import com.tencent.kmm.transportservice.TransportRequestBody.Companion.toRequestBody
import com.tencent.kmm.transportservice.TransportService
import com.tencent.kmm.transportservice.TransportURL
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequest
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResponse
import com.tencent.qqlive.kmm.vbpbservice.export.VBTransportReportInfo
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBQUICConfig
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBytesRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBytesResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportContentType
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportGetRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportGetResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportPostRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportPostResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportStringRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportStringResponse

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * iOS平台对于传输能力的实现桥接
 *
 * @author melontao
 * @date 2024/3/1 19:41
 */
val iOSTransportImpl: IVBTransportService = IOSTransportImpl()

class IOSTransportImpl : IVBTransportService {
    private var logImpl: ((tag: String, content: String) -> Unit)? = null

    override fun post(
        kmmPostRequest: VBTransportPostRequest,
        kmmPostResponseCallback: (
            response: VBTransportPostResponse,
            transportReportInfo: VBTransportReportInfo?
                ) -> Unit
    ) {
        val requestId = kmmPostRequest.requestId
        val logTag = kmmPostRequest.logTag
        val urlString = kmmPostRequest.url
        if (urlString.isEmpty()) throw IllegalArgumentException("URL cannot be empty")
        if (!kmmPostRequest.isDataInitialize()) {
            throw IllegalArgumentException("Data is not initialized")
        }
        val requestData = when (val data = kmmPostRequest.data) {
            is String -> data.encodeToByteArray()
            is ByteArray -> data
            else -> throw IllegalArgumentException("Unsupported data type")
        }
        val dataSize = requestData.size
        log(
            TAG,
            "$logTag send post request, id:$requestId,url:$urlString,data size:$dataSize"
        )
        val url = TransportURL(urlString)
        val transportRequestBody = requestData.toRequestBody(TransportHTTPContentType.APP_STREAM)
        val transportRequest = TransportRequest(
            requestId = requestId,
            url = url,
            method = TransportHTTPMethod.POST,
            headers = kmmPostRequest.header,
            body = transportRequestBody,
            timeoutMS = kmmPostRequest.totalTimeout.toInt(),
            directIpEnabled = true
        )
        TransportService.send(request = transportRequest) { request, response, metrics ->
            val kmmResponse = VBTransportPostResponse()
            kmmResponse.request = kmmPostRequest
            val error = response.error
            if (error == null) {
                val bodyBytes = response.body?.bytes()
                log(TAG, "$logTag request success, body size: ${bodyBytes?.size ?: 0}, " +
                        "response headers: ${response.headers}")
                kmmResponse.errorCode = 0
                kmmResponse.header = response.headers.mapValues { listOf(it.value) }
                val headers = request.headers.mapKeys { it.key.lowercase() }
                kmmResponse.data = if (bodyBytes != null && headers.containsKey("content-type") &&
                    headers["content-type"] == VBTransportContentType.JSON.toString()) {
                    bodyBytes.decodeToString()
                } else {
                    bodyBytes
                }
                kmmPostResponseCallback(kmmResponse, covertNativeMetricsToKmmMetrics(metrics))
            } else {
                log(TAG, "$logTag request fail, $error")
                kmmResponse.errorCode = error.errorCode
                kmmResponse.errorMessage = error.errorMessage
                kmmPostResponseCallback(kmmResponse, covertNativeMetricsToKmmMetrics(metrics))
            }
        }
    }

    override fun get(
        kmmGetRequest: VBTransportGetRequest,
        kmmGetResponseCallback: (
            response: VBTransportGetResponse,
            transportReportInfo: VBTransportReportInfo?
                ) -> Unit
    ) {
        val requestId = kmmGetRequest.requestId
        val logTag = kmmGetRequest.logTag
        val urlString = kmmGetRequest.url
        if (urlString.isEmpty()) throw IllegalArgumentException("URL cannot be empty")
        log(
            TAG,
            "$logTag send get request, id:$requestId, url:$urlString"
        )
        val url = TransportURL(urlString)
        val transportRequest = TransportRequest(
            requestId = requestId,
            url = url,
            method = TransportHTTPMethod.GET,
            headers = kmmGetRequest.header,
            timeoutMS = kmmGetRequest.totalTimeout.toInt(),
            directIpEnabled = true
        )
        TransportService.send(request = transportRequest) { request, response, metrics ->
            val kmmResponse = VBTransportGetResponse()
            kmmResponse.request = kmmGetRequest
            val error = response.error
            if (error == null) {
                log(TAG, "$logTag request success")
                kmmResponse.errorCode = 0
                kmmResponse.header = response.headers.mapValues { listOf(it.value) }
                kmmResponse.data = response.body?.bytes()
                kmmGetResponseCallback(kmmResponse, covertNativeMetricsToKmmMetrics(metrics))
            } else {
                log(TAG, "$logTag request fail, $error")
                kmmResponse.errorCode = error.errorCode
                kmmResponse.errorMessage = error.errorMessage
                kmmGetResponseCallback(kmmResponse, covertNativeMetricsToKmmMetrics(metrics))
            }
        }
    }

    override fun sendBytesRequest(
        kmmBytesRequest: VBTransportBytesRequest,
        kmmBytesResponseCallback: (
            response: VBTransportBytesResponse,
            transportReportInfo: VBTransportReportInfo?
                ) -> Unit
    ) {
        val postRequest = VBTransportPostRequest()
        postRequest.logTag = kmmBytesRequest.logTag
        postRequest.requestId = kmmBytesRequest.requestId
        postRequest.header = kmmBytesRequest.header
        postRequest.url = kmmBytesRequest.url
        postRequest.data = kmmBytesRequest.data
        postRequest.totalTimeout = kmmBytesRequest.totalTimeout
        post(postRequest) { postResponse, transportRepostInfo ->
            val bytesResponse = VBTransportBytesResponse()
            bytesResponse.request = kmmBytesRequest
            bytesResponse.errorCode = postResponse.errorCode
            bytesResponse.errorMessage = postResponse.errorMessage
            bytesResponse.header = postResponse.header
            if (postResponse.data is ByteArray) {
                bytesResponse.data = postResponse.data as ByteArray
            } else if (postResponse.data is String) {
                bytesResponse.data = (postResponse.data as String).encodeToByteArray()
            }
            kmmBytesResponseCallback(bytesResponse, transportRepostInfo)
        }
    }

    override fun sendStringRequest(
        kmmStringRequest: VBTransportStringRequest,
        kmmStringResponseCallback: (
            response: VBTransportStringResponse,
            transportReportInfo: VBTransportReportInfo?
                ) -> Unit
    ) {
        val getRequest = VBTransportGetRequest()
        getRequest.logTag = kmmStringRequest.logTag
        getRequest.requestId = kmmStringRequest.requestId
        getRequest.header = kmmStringRequest.header
        getRequest.url = kmmStringRequest.url
        getRequest.totalTimeout = kmmStringRequest.totalTimeout
        get(getRequest) { getResponse, transportReportInfo ->
            val stringResponse = VBTransportStringResponse()
            stringResponse.request = kmmStringRequest
            stringResponse.errorCode = getResponse.errorCode
            stringResponse.errorMessage = getResponse.errorMessage
            stringResponse.header = getResponse.header
            if (getResponse.data is ByteArray) {
                stringResponse.data = (getResponse.data as ByteArray).decodeToString()
            }
            kmmStringResponseCallback(stringResponse, transportReportInfo)
        }
    }

    override fun <REQUEST : Message<*, *>, RESPONSE : Message<*, *>> sendPBRequest(
        kmmPBRequest: VBPBRequest<REQUEST, RESPONSE>,
        kmmPBResponseCallback: (response: VBPBResponse<RESPONSE>, transportReportInfo: VBTransportReportInfo?) -> Unit
    ) {
        val requestId = kmmPBRequest.requestId
        val logTag = kmmPBRequest.logTag
        val callee = kmmPBRequest.callee
        val func = kmmPBRequest.func
        val urlString = getPBRequestUrl(kmmPBRequest)
        val requestHttpHeaders = kmmPBRequest.requestConfig.httpHeaderMap ?: mapOf()
        val requestData = kmmPBRequest.requestData as ByteArray
        val dataSize = requestData.size
        log(
            TAG,
            "$logTag send request, id:$requestId,url:$urlString,callee:$callee,func:$func,data size:$dataSize"
        )
        val url = TransportURL(urlString)
        val transportRequestBody = requestData.toRequestBody(TransportHTTPContentType.APP_STREAM)
        val protocolStrategy =
            if (kmmPBRequest.requestConfig.quicForceQuic)
                TransportHTTPProtocolStrategy.HTTP3
            else if (VBPBQUICConfig.shouldTryToUseQUIC(kmmPBRequest))
                TransportHTTPProtocolStrategy.HTTP3_WITH_AUTOMATIC_DEGRADATION
            else
                TransportHTTPProtocolStrategy.DEFAULT
        val transportRequest = TransportRequest(
            requestId = requestId,
            url = url,
            method = TransportHTTPMethod.POST,
            headers = requestHttpHeaders,
            body = transportRequestBody,
            protocolStrategy = protocolStrategy,
            timeoutMS = kmmPBRequest.requestConfig.totalTimeout.toInt(),
            directIpEnabled = true
        )
        TransportService.send(request = transportRequest) { request, response, metrics ->
            val responseBody = response.body
            val error = response.error
            val kmmResponse = VBPBResponse<RESPONSE>()
            if (error == null) {
                log(TAG, "$logTag request success")
                kmmResponse.errorCode = 0
                kmmResponse.rawBytes = responseBody?.bytes()
            } else {
                log(TAG, "$logTag request fail, $error")
                kmmResponse.errorCode = error.errorCode
                kmmResponse.errorMessage = error.errorMessage
                kmmResponse.errorCodeType = error.errorType
            }
            kmmPBResponseCallback(kmmResponse, covertNativeMetricsToKmmMetrics(metrics))
        }
    }

    override fun cancel(requestId: Int, useCurl: Boolean) {
        TransportService.cancel(requestId)
    }

    override fun setLogImpl(kmmImpl: (tag: String, content: String) -> Unit) {
        this.logImpl = kmmImpl
    }

    override fun getNetworkType(): Int = NETWORK_TYPE_NET_UNKNOWN

    private fun log(tag: String, content: String) {
        logImpl?.let { it(tag, content) }
    }

    private fun covertNativeMetricsToKmmMetrics(nativeMetrics: TransportMetrics): VBTransportReportInfo {
        return VBTransportReportInfo().apply {
            errorCode = nativeMetrics.error?.errorCode.toString()
            errorMessage = nativeMetrics.error?.errorMessage
            errorType = nativeMetrics.error?.errorType
            url = nativeMetrics.url?.urlString
            domain = nativeMetrics.domain
            totalCost = nativeMetrics.totalCostMS
            dnsCost = nativeMetrics.dnsCostMS
            connCost = nativeMetrics.connectCostMS
            sslCost = nativeMetrics.sslCostMS
            queueCost = nativeMetrics.queueCostMS
            sendCost = nativeMetrics.sendCostMS
            firstByteCost = nativeMetrics.waitCostMS
            recvCost = nativeMetrics.receiveCostMS
            transProtocol = nativeMetrics.protocolName
            transProtocolStrategy = nativeMetrics.protocolStrategy
            transDegradationType = nativeMetrics.degradationType
            reusedConnection = nativeMetrics.reusedConnection
//            localNetStackType
//            useNetCardType
            respContentType = nativeMetrics.responseContentType
            httpStatusCode = nativeMetrics.httpStatusCode
            isHttps = nativeMetrics.isHttps
            httpVer = nativeMetrics.protocolName
//            tlsVer
            targetIp = nativeMetrics.ip
            ipType = nativeMetrics.ipType
            upstreamSize = nativeMetrics.requestSize
            downstreamSize = nativeMetrics.responseSize
            requestBodySize = nativeMetrics.requestBodySize
            responseBodySize = nativeMetrics.responseBodySize
            serverIP = nativeMetrics.serverIP
            serverPort = nativeMetrics.serverPort
        }
    }

    private fun <R : Message<*, *>, T : Message<*, *>> getPBRequestUrl(kmmRequest: VBPBRequest<R, T>): String {
        if (kmmRequest.requestConfig.url.isEmpty()) {
            val isHttps = kmmRequest.requestConfig.isHttps
            val scheme = if (isHttps) "https://" else "http://"
            val domain = kmmRequest.requestConfig.domain
            return "$scheme$domain"
        }
        return kmmRequest.requestConfig.url
    }

    companion object {
        const val TAG = "Transport_iOS"
    }
}

actual fun getIVBTransportService(): IVBTransportService = iOSTransportImpl