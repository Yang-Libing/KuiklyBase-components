package com.tencent.qqlive.kmm.vbpbservice.impl.platform

import com.squareup.wire.kmm.Message
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequest
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResponse
import com.tencent.qqlive.kmm.vbpbservice.export.VBTransportReportInfo
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBLog
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBProtocolType
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBytesRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBytesResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportContentType
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportGetRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportGetResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportPostRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportPostResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportStringRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportStringResponse
import com.tencent.qqlive.modules.vb.transportservice.export.VBTransportMethod
import com.tencent.qqlive.modules.vb.transportservice.export.listener.IVBTransportTextListener
import com.tencent.qqlive.modules.vb.transportservice.export.request.VBTransportRequestBodyRequest
import com.tencent.qqlive.modules.vb.transportservice.export.response.VBTransportBaseResponse
import com.tencent.qqlive.modules.vb.transportservice.impl.VBTransportManager
import com.tencent.qqlive.modules.vb.transportservice.impl.VBTransportProtocolType
import com.tencent.qqlive.modules.vb.transportservice.impl.VBTransportResultCode
import com.tencent.qqlive.modules.vb.transportservice.impl.newclient.VBHttpEngineNetState
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody


/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * Android平台对于传输能力的实现桥接
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
var androidTransportImpl: IVBTransportService? = null

class AndroidTransportImpl : IVBTransportService {
    private var logImpl: ITransportLog? = null

    override fun <REQUEST : Message<*, *>, RESPONSE : Message<*, *>> sendPBRequest(
        kmmRequest: VBPBRequest<REQUEST, RESPONSE>,
        kmmCallback: (response: VBPBResponse<RESPONSE>, transportReportInfo: VBTransportReportInfo?) -> Unit
    ) {
        // 请求url校验
        checkRequestUrl(kmmRequest)
        val androidRequest =
            com.tencent.qqlive.modules.vb.transportservice.export.request.VBTransportBytesRequest()
        if (kmmRequest.requestConfig.protocolType == VBPBProtocolType.QUIC) {
            androidRequest.protocolType = VBTransportProtocolType.QUIC
        }
        androidRequest.isQuicForceQuic = kmmRequest.requestConfig.quicForceQuic
        androidRequest.requestId = kmmRequest.requestId.toLong()
        androidRequest.data = kmmRequest.requestData
        androidRequest.address = kmmRequest.requestConfig.url
        androidRequest.tag = kmmRequest.logTag
        androidRequest.header = kmmRequest.requestConfig.httpHeaderMap
        androidRequest.setConnTimeOut((kmmRequest.requestConfig.connTimeOut.toDouble() / 1000))
        androidRequest.setReadTimeOut((kmmRequest.requestConfig.readTimeOut.toDouble() / 1000))
        androidRequest.setWriteTimeOut((kmmRequest.requestConfig.writeTimeOut.toDouble() / 1000))
        val extraHeaders = HashMap<String, List<String>>()
        kmmRequest.requestConfig.extraDataMap?.forEach {
            it.key?.let { key ->
                extraHeaders.put(key, mutableListOf(it.value))
            }
        }
        androidRequest.extraHeaders = extraHeaders
        log(
            "NXNetwork_Android",
            "${kmmRequest.logTag} send request, id:${kmmRequest.requestId} ,url:${kmmRequest.requestConfig.url} , callee:${kmmRequest.callee}, func:${kmmRequest.func}, data size:${kmmRequest.requestData?.size}"
        )
        VBTransportManager.getInstance()
            .sendRequestWithBytes(androidRequest) { androidError, androidResponse ->
                val responseCode = androidError.errorCode
                val responseMessage = androidError.errorDesc
                val responseData = androidResponse.data
                log(
                    "NXNetwork_Android",
                    "${kmmRequest.logTag} receive response" + ", code:$responseCode" + ", message:$responseMessage" + ", size:${responseData?.size ?: -1}"
                )
                val kmmResponse = VBPBResponse<RESPONSE>()
                kmmResponse.errorCode = responseCode
                kmmResponse.errorMessage = androidError.errorDesc
                kmmResponse.errorCodeType = androidError.errorCodeType
                kmmResponse.rawBytes = responseData
                // Android上报结构转KMM上报结构
                val kmmTransportReportInfo =
                    convertKmmTransportReportInfo(androidResponse.reportInfo)
                kmmCallback(kmmResponse, kmmTransportReportInfo)
            }
    }

    private fun convertKmmTransportReportInfo(responseReportInfo: com.tencent.qqlive.modules.vb.transportservice.export.VBTransportReportInfo?): VBTransportReportInfo? {
        return responseReportInfo?.let { androidReportInfo ->
            VBTransportReportInfo().apply {
                // Android 原组件url和domain未区分
                url = androidReportInfo.requestDomain
                domain = androidReportInfo.requestDomain
                targetIp = androidReportInfo.requestIp
                dnsCost = androidReportInfo.dnsTimeSpent.toString()
                connCost = androidReportInfo.socketConnTimeSpent.toString()
                sslCost = androidReportInfo.tlsConnTimeSpent.toString()
                queueCost = androidReportInfo.queueUpTimeSpent.toString()
                sendCost = androidReportInfo.requestTimeSpent.toString()
                firstByteCost = androidReportInfo.rttTimeSpent.toString()
                recvCost = androidReportInfo.responseTimeSpent.toString()
                transProtocol = androidReportInfo.httpVersion
                transDegradationType = androidReportInfo.disableQuicReason.toString()
                transProtocolStrategy =
                    if (androidReportInfo.useQuicClient) TRANS_PROTOCOL_STRATEGY_QUIC.toString()
                    else TRANS_PROTOCOL_STRATEGY_HTTP.toString()
                // Android 原组件暂缺此信息
                // local_net_stack_type = androidReportInfo.localStackType.toString()
                useNetCardType =
                    if (VBHttpEngineNetState.isWifi()) NETWORK_TYPE_WIFI.toString() else NETWORK_TYPE_CELLULAR.toString()
                respContentType = androidReportInfo.actualContentType ?: ""
                httpStatusCode = androidReportInfo.errorCode.toString()
                isHttps = if (androidReportInfo.isIsHttps) "1" else "0"
                httpVer = androidReportInfo.httpVersion
                totalCost = androidReportInfo.callTimeSpent.toString()
                disableQuicReason = androidReportInfo.disableQuicReason.toString()
                useQuicClient = if (androidReportInfo.useQuicClient) "1" else "0"
            }
        }
    }

    override fun sendBytesRequest(
        kmmRequest: VBTransportBytesRequest,
        kmmeCallback: (
            kmmResponse: VBTransportBytesResponse,
            transportReportInfo: VBTransportReportInfo?
        ) -> Unit
    ) {
        val androidRequest =
            com.tencent.qqlive.modules.vb.transportservice.export.request.VBTransportBytesRequest()
        androidRequest.isQuicForceQuic = kmmRequest.quicForceQuic
        androidRequest.requestId = kmmRequest.requestId.toLong()
        androidRequest.data = kmmRequest.data
        androidRequest.address = kmmRequest.url
        androidRequest.setTag(kmmRequest.logTag)
        androidRequest.setHeader(kmmRequest.header)
        androidRequest.setRetainCallBackListener(true)
        androidRequest.setConnTimeOut((kmmRequest.totalTimeout.toDouble() / 1000))
        androidRequest.setReadTimeOut((kmmRequest.totalTimeout.toDouble() / 1000))
        androidRequest.setWriteTimeOut((kmmRequest.totalTimeout.toDouble() / 1000))
        log(
            "NXNetwork_Android",
            "${kmmRequest.logTag} send request, id:${kmmRequest.requestId} ,url:${kmmRequest.url}, data size:${kmmRequest.data.size}"
        )
        VBTransportManager.getInstance()
            .sendRequestWithBytes(androidRequest) { androidError, androidResponse ->
                val responseCode = androidError.errorCode
                val responseMessage = androidError.errorDesc
                val responseData = androidResponse?.data ?: byteArrayOf()
                log(
                    "NXNetwork_Android",
                    "${kmmRequest.logTag} receive response" + ", code:$responseCode" + ", message:$responseMessage" + ", size:${responseData?.size ?: -1}"
                )

                // Android上报结构转KMM上报结构
                val kmmTransportReportInfo = convertKmmTransportReportInfo(androidResponse.reportInfo)

                val kmmResponse = VBTransportBytesResponse()
                kmmResponse.request = kmmRequest
                kmmResponse.errorCode = responseCode
                kmmResponse.errorMessage = androidError.errorDesc
                kmmResponse.data = responseData
                kmmeCallback(kmmResponse, kmmTransportReportInfo)
            }
    }

    override fun sendStringRequest(
        kmmRequest: VBTransportStringRequest,
        kmmCallback: (
            kmmResponse: VBTransportStringResponse,
            transportReportInfo: VBTransportReportInfo?
        ) -> Unit
    ) {
        val androidRequest =
            com.tencent.qqlive.modules.vb.transportservice.export.request.VBTransportJsonRequest()
        androidRequest.isQuicForceQuic = kmmRequest.quicForceQuic
        androidRequest.requestId = kmmRequest.requestId.toLong()
        androidRequest.address = kmmRequest.url
        androidRequest.header = kmmRequest.header
        androidRequest.tag = kmmRequest.logTag
        androidRequest.method = VBTransportMethod.GET
        androidRequest.setConnTimeOut((kmmRequest.totalTimeout.toDouble() / 1000))
        androidRequest.setReadTimeOut((kmmRequest.totalTimeout.toDouble() / 1000))
        androidRequest.setWriteTimeOut((kmmRequest.totalTimeout.toDouble() / 1000))
        VBTransportManager.getInstance().sendRequestWithJson(androidRequest,
            IVBTransportTextListener { androidError, androidResponse ->
                val responseCode = androidError.errorCode
                val responseMessage = androidError.errorDesc
                val responseData = androidResponse?.data ?: ""
                log(
                    "NXNetwork_Android",
                    "${androidRequest.tag} receive response" + ", code:$responseCode" + ", message:$responseMessage" + ", size:${responseData.length}"
                )

                // Android上报结构转KMM上报结构
                val kmmTransportReportInfo = convertKmmTransportReportInfo(androidResponse.reportInfo)

                val kmmResponse = VBTransportStringResponse()
                kmmResponse.request = kmmRequest
                kmmResponse.errorCode = responseCode
                kmmResponse.errorMessage = responseMessage
                kmmResponse.data = responseData
                kmmCallback(kmmResponse, kmmTransportReportInfo)
            })
    }

    private fun getResponseData(contentType: String?, responseBytes: ByteArray): Any {
        return when {
            contentType?.contains(
                VBTransportContentType.JSON.toString(),
                ignoreCase = true
            ) == true ->
                responseBytes.decodeToString()

            else ->
                responseBytes
        }
    }

    private fun <D> handleResponse(
        androidResponse: VBTransportBaseResponse<D>?,
        kmmResponse: com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBaseResponse,
        contentType: String?
    ) {
        androidResponse?.let {
            it.responseHeaders?.let { headers ->
                kmmResponse.header = headers
            }
            it.originalResponse?.body?.let { body ->
                try {
                    val responseBytes = body.bytes()
                    if (kmmResponse is VBTransportPostResponse) {
                        kmmResponse.data = getResponseData(contentType, responseBytes)
                    } else if (kmmResponse is VBTransportGetResponse) {
                        kmmResponse.data = getResponseData(contentType, responseBytes)
                    }
                } catch (e: Throwable) {
                    kmmResponse.errorCode = VBTransportResultCode.CODE_HTTP_IOERR
                    kmmResponse.errorMessage = e.message ?: ""
                    VBPBLog.i(
                        "NXNetwork_Android",
                        "handleResponse fail, code:${kmmResponse.errorCode}, message: ${kmmResponse.errorMessage}"
                    )
                }
            }
        }
    }

    override fun post(
        kmmRequest: VBTransportPostRequest,
        kmmCallback: (
            kmmResponse: VBTransportPostResponse,
            transportReportInfo: VBTransportReportInfo?
                ) -> Unit
    ) {
        val contentType =
            kmmRequest.header["Content-Type"] ?: VBTransportContentType.BYTE.toString()
        val requestBody: RequestBody
        if (contentType.contains(VBTransportContentType.JSON.toString())) {
            requestBody = RequestBody.create(contentType.toMediaType(), kmmRequest.data as String)
        } else {
            requestBody =
                RequestBody.create(contentType.toMediaType(), kmmRequest.data as ByteArray)
        }
        val androidRequest = VBTransportRequestBodyRequest()
        androidRequest.isQuicForceQuic = kmmRequest.quicForceQuic
        androidRequest.data = requestBody
        androidRequest.method = VBTransportMethod.POST
        androidRequest.header = kmmRequest.header
        androidRequest.address = kmmRequest.url
        androidRequest.tag = kmmRequest.logTag
        androidRequest.requestId = kmmRequest.requestId.toLong()
        androidRequest.setReturnOriginalResponse(true)
        androidRequest.setConnTimeOut((kmmRequest.totalTimeout.toDouble() / 1000))
        androidRequest.setReadTimeOut((kmmRequest.totalTimeout.toDouble() / 1000))
        androidRequest.setWriteTimeOut((kmmRequest.totalTimeout.toDouble() / 1000))
        VBTransportManager.getInstance()
            .sendRequestWithRequestBody(androidRequest) { androidError, androidResponse ->
                // Android上报结构转KMM上报结构
                val kmmTransportReportInfo = convertKmmTransportReportInfo(androidResponse.reportInfo)

                val kmmResponse = VBTransportPostResponse()
                kmmResponse.request = kmmRequest
                kmmResponse.errorCode = androidError.errorCode
                kmmResponse.errorMessage = androidError.errorDesc
                handleResponse(androidResponse, kmmResponse, androidRequest.header["Content-Type"])
                kmmCallback(kmmResponse, kmmTransportReportInfo)
            }
    }

    override fun get(
        kmmRequest: VBTransportGetRequest,
        kmmCallback: (
            kmmResponse: VBTransportGetResponse,
            transportReportInfo: VBTransportReportInfo?
                ) -> Unit
    ) {
        val androidRequest = VBTransportRequestBodyRequest()
        androidRequest.method = VBTransportMethod.GET
        androidRequest.header = kmmRequest.header
        androidRequest.address = kmmRequest.url
        androidRequest.tag = kmmRequest.logTag
        androidRequest.requestId = kmmRequest.requestId.toLong()
        androidRequest.setReturnOriginalResponse(true)
        androidRequest.setConnTimeOut((kmmRequest.totalTimeout.toDouble() / 1000))
        androidRequest.setReadTimeOut((kmmRequest.totalTimeout.toDouble() / 1000))
        androidRequest.setWriteTimeOut((kmmRequest.totalTimeout.toDouble() / 1000))
        VBTransportManager.getInstance()
            .sendRequestWithRequestBody(androidRequest) { androidError, androidResponse ->
                // Android上报结构转KMM上报结构
                val kmmTransportReportInfo = convertKmmTransportReportInfo(androidResponse.reportInfo)

                val kmmResponse = VBTransportGetResponse()
                kmmResponse.request = kmmRequest
                androidResponse?.originalResponse?.request?.url?.let {
                    kmmResponse.request.url = it.toString()
                }
                kmmResponse.errorCode = androidError.errorCode
                kmmResponse.errorMessage = androidError.errorDesc
                handleResponse(androidResponse, kmmResponse, androidRequest.header["Content-Type"])
                kmmCallback(kmmResponse, kmmTransportReportInfo)
            }
    }

    // 请求url校验
    private fun <R : Message<*, *>, T : Message<*, *>> checkRequestUrl(kmmRequest: VBPBRequest<R, T>) {
        if (kmmRequest.requestConfig.url.isEmpty()) {
            val isHttps = kmmRequest.requestConfig.isHttps
            val scheme = if (isHttps) "https://" else "http://"
            val domain = kmmRequest.requestConfig.domain
            kmmRequest.requestConfig.url = "$scheme$domain"
        }
    }

    private fun log(tag: String, content: String) {
        logImpl?.log(tag, content)
    }

    override fun setLogImpl(kmmImpl: (tag: String, content: String) -> Unit) {
        this.logImpl = object : ITransportLog {
            override fun log(tag: String, content: String) {
                kmmImpl(tag, content)
            }
        }
    }

    override fun cancel(requestId: Int, useCurl: Boolean) {
        VBTransportManager.getInstance().cancel(requestId.toLong())
    }

    // TODO 待对接PlatformUtils，改为正确数值
    override fun getNetworkType() = NETWORK_TYPE_NET_UNKNOWN

    interface ITransportLog {
        fun log(tag: String, content: String)
    }

    companion object {
        const val TRANS_PROTOCOL_STRATEGY_HTTP = 1
        const val TRANS_PROTOCOL_STRATEGY_QUIC = 2
        const val NETWORK_TYPE_WIFI = 1
        const val NETWORK_TYPE_CELLULAR = 2
    }
}

actual fun getIVBTransportService(): IVBTransportService {
    if (androidTransportImpl == null) {
        androidTransportImpl = AndroidTransportImpl()
    }
    return androidTransportImpl as IVBTransportService
}