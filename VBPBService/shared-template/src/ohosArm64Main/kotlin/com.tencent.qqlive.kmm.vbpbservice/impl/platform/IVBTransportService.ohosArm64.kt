package com.tencent.qqlive.kmm.vbpbservice.impl.platform

import com.squareup.wire.kmm.Message
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBNetworkState
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequest
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequestConfig
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResponse
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResultCode.NETWORK_DISCONNECT
import com.tencent.qqlive.kmm.vbpbservice.export.VBTransportReportInfo
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBNetworkUtils.getRedirectedUrl
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBHeaderConfig.Companion.convertToNetworkType
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBLog
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBProtocolType
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.encodeToJsonString
import com.tencent.qqlive.kmm.vbtransportservice.curl.CurlRequestService
import com.tencent.qqlive.kmm.vbtransportservice.curl.CurlRequestServiceHM.wrapBytesCallback
import com.tencent.qqlive.kmm.vbtransportservice.curl.CurlRequestServiceHM.wrapGetCallback
import com.tencent.qqlive.kmm.vbtransportservice.curl.CurlRequestServiceHM.wrapPostCallback
import com.tencent.qqlive.kmm.vbtransportservice.curl.CurlRequestServiceHM.wrapStringCallback
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBaseRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBaseResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBytesRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBytesResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportContentType
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportGetRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportGetResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportPostRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportPostResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportStringRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportStringResponse
import com.tencent.tmm.knoi.converter.knoiDispose
import com.tencent.tmm.knoi.type.ArrayBuffer
import com.tencent.tmm.knoi.type.JSValue
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.convert
import kotlinx.cinterop.nativeHeap
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonPrimitive
import platform.ohos.napi_typedarray_type
import platform.posix.int8_tVar
import platform.posix.memcpy
import kotlin.collections.set

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * 鸿蒙平台对于网络请求能力的实现
 *
 * @author haibarawang
 * @date 2024/4/13 14:41
 */

// 跨端请求传递Map对应的KEY
const val REQUEST_ID = "requestId"
const val REQUEST_METHOD = "method"
const val REQUEST_IP = "ip"
const val REQUEST_ADDRESS = "address"
const val REQUEST_DNS_TIMEOUT = "dnsTimeout"
const val REQUEST_CONN_TIMEOUT = "connTimeout"
const val REQUEST_READ_TIMEOUT = "readTimeout"
const val REQUEST_WRITE_TIMEOUT = "writeTimeout"
const val REQUEST_TAG = "tag"
const val REQUEST_CONTENT_TYPE = "contentType"
const val KMM_REQUEST_CONTENT_TYPE = "Content-Type"
const val REQUEST_DATA = "data"
const val REQUEST_HEADER = "header"
const val REQUEST_PROTOCOLTYPE = "protocoltype"

// 跨端响应传递Map对应的Key
const val RESPONSE_CODE = "code"
const val RESPONSE_MESSAGE = "message"
const val RESPONSE_ADDRESS = "url"
const val RESPONSE_DNSTIMING = "dnsTiming"
const val RESPONSE_TCPTIMING = "tcpTiming"
const val RESPONSE_TLSTIMING = "tlsTiming"
const val RESPONSE_TOTALTIMING = "totalTiming"
const val RESPONSE_DATA = "data"
const val RESPONSE_SEND_TIME = "sendTime"
const val RESPONSE_HEADERS = "headers"
const val HEADER_LOCATION = "location"

// 响应类型
const val BYTE_RESPONSE = "VBTransportByteResponse"
const val STRING_RESPONSE = "VBTransportStringResponse"
const val POST_RESPONSE = "VBTransportPostResponse"
const val GET_RESPONSE = "VBTransportGetResponse"

var hmTransportImpl: IVBTransportService? = null

class HmTransportImpl : IVBTransportService {

    private val scope = CoroutineScope(Dispatchers.IO)

    // 响应数据异常类
    class ResultArrayException(message: String) : Exception(message)

    fun setupTimeoutJob(scope: CoroutineScope, logTag: String?, callback: () -> Unit): Job {
        return scope.launch(track = true) {
            delay(60000)
            logI("[${logTag}] request fail, knoi dispose")
            callback()
        }
    }

    override fun <REQUEST : Message<*, *>, RESPONSE : Message<*, *>> sendPBRequest(
        kmmPBRequest: VBPBRequest<REQUEST, RESPONSE>,
        kmmPBResponseCallback: (
            response: VBPBResponse<RESPONSE>,
            transportReportInfo: VBTransportReportInfo?
                ) -> Unit
    ) {
        val logTag = kmmPBRequest.logTag
        // 请求url校验
        checkRequestUrl(kmmPBRequest)

        // 使用 libcurl 进行请求
        if (kmmPBRequest.useCurl) {
            CurlRequestService.sendPBRequest(kmmPBRequest, kmmPBResponseCallback, logTag ?: "")
            return
        }

        // 请求时 KMM 转 ArkTs 结构向鸿蒙跨端传输
        val hmRequestMap = createHmPBRequest(kmmPBRequest)
        lateinit var callbackPbRequest: (Array<JSValue?>) -> Unit
        // 建立延时任务，若请求失败则删除请求，避免内存泄漏
        val job = setupTimeoutJob(scope, logTag) {
            callbackPbRequest.knoiDispose()
        }
        callbackPbRequest = { resultArray: Array<JSValue?> ->
            try {
                callbackPbRequest.knoiDispose()
                job.cancel()
                logI("[${logTag}]  receive hm response array size: ${resultArray.size}")
                if (resultArray.size != 2) {
                    throw error("response size less than 2")
                }
                var hmResponse: Map<String, Any?>? = null
                var transportReportInfo: VBTransportReportInfo? = null
                try {
                    // 检查返回结果有无异常
                    checkResult(resultArray)
                    // 响应数据 Map
                    hmResponse = resultArray[0]?.toMap() ?: mutableMapOf()
                    // 更新上报信息
                    transportReportInfo = getTransportReportInfo(hmResponse)
                } catch (e: Exception) {
                    // 响应数据出错
                    val networkSate = checkNetwork()
                    val errorInfo: MutableMap<String, Any?> =
                        resultArray[1]?.toMap()?.toMutableMap() ?: mutableMapOf()
                    logI("[$logTag] network-state: $networkSate")
                    if (networkSate == VBPBNetworkState.NETWORK_STATE_DISCONNECT) {
                        errorInfo[RESPONSE_CODE] = NETWORK_DISCONNECT
                        errorInfo[RESPONSE_MESSAGE] = "network disconnect!"
                        logI("[${logTag}] network disconnect :$networkSate")
                    }
                    kmmPBResponseCallback(
                        createPBResponse<RESPONSE>(errorInfo, hmResponse),
                        transportReportInfo
                    )
                    throw error("response error")
                }
                // 异常信息 Map
                val errorInfo = resultArray[1]?.toMap() ?: mutableMapOf()
                // 构造响应信息
                val kmmPBResponse = createPBResponse<RESPONSE>(errorInfo, hmResponse)
                // 回调上层
                kmmPBResponseCallback(kmmPBResponse, transportReportInfo)
                logI("[${logTag}] invoke callback")
            } catch (error: Exception) {
                logI("[${logTag}] ${error.message}")
            }
        }
        // 跨端调用
        getIHMTransportServiceApi().sendPostRequest(hmRequestMap, callbackPbRequest)
    }

    override fun sendBytesRequest(
        kmmBytesRequest: VBTransportBytesRequest,
        kmmBytesResponseCallback: (
            response: VBTransportBytesResponse,
            transportReportInfo: VBTransportReportInfo?
                ) -> Unit
    ) {
        val logTag = kmmBytesRequest.logTag + "_" + kmmBytesRequest.requestId
        // 使用 libcurl 进行请求
        if (kmmBytesRequest.useCurl) {
            CurlRequestService.sendBytesRequest(kmmBytesRequest, kmmBytesResponseCallback, logTag)
            return
        }
        
        // 请求时 KMM 转 ArkTs 结构向鸿蒙跨端传输
        val hmRequestMap = createHmBytesRequest(kmmBytesRequest)
        logI("[${logTag}] send bytes request:${kmmBytesRequest.requestId}, request-map: $hmRequestMap")
        val callbackBytesRequest: (Array<JSValue?>) -> Unit = buildCallbackRequest(kmmBytesRequest,
            wrapBytesCallback(kmmBytesResponseCallback), logTag)
        // 响应时 ArkTs 转 KMM 结构
        getIHMTransportServiceApi().sendPostRequest(hmRequestMap, callbackBytesRequest)
    }

    override fun sendStringRequest(
        kmmStringRequest: VBTransportStringRequest,
        kmmStringResponseCallback: (
            response: VBTransportStringResponse,
            transportReportInfo: VBTransportReportInfo?
                ) -> Unit
    ) {
        val logTag = kmmStringRequest.logTag + "_" + kmmStringRequest.requestId
        logI("[${logTag}] send bytes request: ${kmmStringRequest.requestId}")

        // 使用 libcurl 进行请求
        if (kmmStringRequest.useCurl) {
            CurlRequestService.sendStringRequest(kmmStringRequest, kmmStringResponseCallback, logTag)
            return
        }

        // 请求时 KMM 转 ArkTs 结构向鸿蒙跨端传输
        val hmRequestMap = createStringRequest(kmmStringRequest)
        val callbackStringRequest: (Array<JSValue?>) -> Unit = buildCallbackRequest(kmmStringRequest,
            wrapStringCallback(kmmStringResponseCallback), logTag)
        // 响应时 ArkTs 转 KMM 结构
        getIHMTransportServiceApi().sendGetRequest(hmRequestMap, callbackStringRequest)
    }

    private fun buildCallbackRequest(
        request: VBTransportBaseRequest,
        callback: (
            response: VBTransportBaseResponse,
            transportReportInfo: VBTransportReportInfo?
        ) -> Unit,
        logTag: String
    ): (Array<JSValue?>) -> Unit {
        lateinit var callbackStringRequest: (Array<JSValue?>) -> Unit
        // 建立延时任务，若请求失败则删除请求，避免内存泄漏
        val job = setupTimeoutJob(scope, logTag) {
            callbackStringRequest.knoiDispose()
        }
        callbackStringRequest = { resultArray ->
            try {
                callbackStringRequest.knoiDispose()
                job.cancel()
                logI("[${logTag}]  receive hm response array size: ${resultArray.size}")
                if (resultArray.size != 2) {
                    logI("[${logTag}]  receive hm response array size: size is not 2")
                    throw error("response size less than 2")
                }
                val hmResponse: Map<String, Any?>?
                // 异常信息 Map
                val errorInfo = resultArray[1]?.toMap() ?: mutableMapOf()
                var transportReportInfo: VBTransportReportInfo? = null
                try {
                    // 检查返回结果有无异常
                    checkResult(resultArray)
                    // 响应数据 Map
                    hmResponse = resultArray[0]?.toMap() ?: mutableMapOf()
                    // 更新上报信息
                    transportReportInfo = getTransportReportInfo(hmResponse)
                } catch (e: Exception) {
                    // 响应数据出错
                    val kmmResponse =
                        createErrorResponse(errorInfo, request, getResponseType(request))
                    callback(kmmResponse, transportReportInfo)
                    throw error("response error")
                }
                // 构造响应信息
                constructResponseAndCallback(
                    request,
                    transportReportInfo,
                    errorInfo,
                    hmResponse,
                    callback
                )
                logI("[${logTag}] invoke callback")
            } catch (error: Exception) {
                logI("[${logTag}] ${error.message}")
            }
        }
        return callbackStringRequest
    }

    private fun getResponseType(request: VBTransportBaseRequest): String {
        return when (request) {
            is VBTransportGetRequest -> GET_RESPONSE
            is VBTransportStringRequest -> STRING_RESPONSE
            is VBTransportPostRequest -> POST_RESPONSE
            is VBTransportBytesRequest -> BYTE_RESPONSE
            else -> GET_RESPONSE
        }
    }

    private fun constructResponseAndCallback(
        request: VBTransportBaseRequest,
        transportReportInfo: VBTransportReportInfo?,
        errorInfo: Map<String, Any?>,
        hmResponse: Map<String, Any?>,
        callback: (
            response: VBTransportBaseResponse,
            transportReportInfo: VBTransportReportInfo?
        ) -> Unit,
    ) {
        if (request is VBTransportGetRequest) {
            // Get 请求回调
            val kmmGetResponse = VBTransportGetResponse().apply {
                updateResponse(errorInfo, hmResponse, request, this)
            }
            callback(kmmGetResponse, transportReportInfo)
        } else if (request is VBTransportStringRequest) {
            // String 请求回调
            val kmmStringResponse = VBTransportStringResponse().apply {
                updateResponse(errorInfo, hmResponse, request, this)
            }
            callback(kmmStringResponse, transportReportInfo)
        } else if (request is VBTransportPostRequest) {
            // Post 请求回调
            val kmmPostResponse = VBTransportPostResponse().apply {
                updateResponse(errorInfo, hmResponse, request, this)
            }
            callback(kmmPostResponse, transportReportInfo)
        } else if ((request is VBTransportBytesRequest)) {
            // Bytes 请求回调
            val kmmBytesResponse = VBTransportBytesResponse().apply {
                updateResponse(errorInfo, hmResponse, request, this)
            }
            callback(kmmBytesResponse, transportReportInfo)
        }
    }

    override fun post(
        kmmPostRequest: VBTransportPostRequest,
        kmmPostResponseCallback: (
            response: VBTransportPostResponse,
            transportReportInfo: VBTransportReportInfo?
                ) -> Unit
    ) {
        val logTag = kmmPostRequest.logTag + "_" + kmmPostRequest.requestId

        // 使用 libcurl 进行请求
        if (kmmPostRequest.useCurl) {
            CurlRequestService.sendPostRequest(kmmPostRequest, kmmPostResponseCallback, logTag)
            return
        }

        // 请求时 KMM 转 ArkTs 结构向鸿蒙跨端传输
        val hmRequestMap = createPostRequest(kmmPostRequest)
        logI("[${logTag}] send post request: ${kmmPostRequest.requestId}，request-map: $hmRequestMap")
        val callbackPostRequest: (Array<JSValue?>) -> Unit = buildCallbackRequest(kmmPostRequest,
            wrapPostCallback(kmmPostResponseCallback), logTag)
        // 响应时 ArkTs 转 KMM 结构
        getIHMTransportServiceApi().sendPostRequest(hmRequestMap, callbackPostRequest)
    }

    override fun get(
        kmmGetRequest: VBTransportGetRequest,
        kmmGetResponseCallback: (
            response: VBTransportGetResponse,
            transportReportInfo: VBTransportReportInfo?
                ) -> Unit
    ) {
        // 使用桥接native的方式进行请求
        val logTag = kmmGetRequest.logTag + "_" + kmmGetRequest.requestId

        // 使用 libcurl 进行请求
        if (kmmGetRequest.useCurl) {
            CurlRequestService.sendGetRequest(kmmGetRequest, kmmGetResponseCallback, logTag)
            return
        }

        // 请求时 KMM 转 ArkTs 结构向鸿蒙跨端传输
        val hmRequestMap = createGetRequest(kmmGetRequest)
        logI("[${logTag}] send get request: ${kmmGetRequest.requestId}, request-map: $hmRequestMap")
        val callbackGetRequest: (Array<JSValue?>) -> Unit = buildCallbackRequest(kmmGetRequest,
            wrapGetCallback(kmmGetResponseCallback), logTag)
        // 响应时 ArkTs 转 KMM 结构
        getIHMTransportServiceApi().sendGetRequest(hmRequestMap, callbackGetRequest)
    }

    private fun checkNetwork(): VBPBNetworkState =
        convertToNetworkType(getIVBTransportService().getNetworkType())

    private fun createErrorResponse(
        errorInfo: Map<String, Any?>,
        requestIn: Any,
        responseType: String
    ): VBTransportBaseResponse {
        val response: VBTransportBaseResponse?
        val logTag: String?
        when (responseType) {
            GET_RESPONSE -> {
                response = VBTransportGetResponse()
                response.request = requestIn as VBTransportGetRequest
                logTag = requestIn.logTag
            }

            POST_RESPONSE -> {
                response = VBTransportPostResponse()
                response.request = requestIn as VBTransportPostRequest
                logTag = requestIn.logTag
            }

            STRING_RESPONSE -> {
                response = VBTransportStringResponse()
                response.request = requestIn as VBTransportStringRequest
                logTag = requestIn.logTag
            }

            BYTE_RESPONSE -> {
                response = VBTransportBytesResponse()
                response.request = requestIn as VBTransportBytesRequest
                logTag = requestIn.logTag
            }

            else -> {
                response = VBTransportBaseResponse()
                logTag = (requestIn as VBTransportBaseRequest).logTag
            }
        }
        val internalNetworkType = checkNetwork()
        if (internalNetworkType == VBPBNetworkState.NETWORK_STATE_DISCONNECT) {
            response.errorCode = NETWORK_DISCONNECT
            response.errorMessage = "network disconnect!"
        } else {
            response.errorCode = ((errorInfo[RESPONSE_CODE] ?: -1) as Double).toInt()
            response.errorMessage = (errorInfo[RESPONSE_MESSAGE] ?: "") as String
        }
        logI("[${logTag}] response data error! network-type:$internalNetworkType , error-message: ${response.errorMessage}")
        return response
    }

    override fun setLogImpl(logImpl: (tag: String, content: String) -> Unit) {
        getIHMTransportServiceApi().setLogImpl {
            if (it.size != 2) {
                return@setLogImpl
            }
            val tag = it[0].toKString()
            val content = it[1].toKString()
            logImpl(tag ?: "", content ?: "")
        }
    }

    override fun cancel(requestId: Int, useCurl: Boolean) {
        logI("cancel request id: $requestId")
        if (useCurl) {
            CurlRequestService.cancel(requestId)
        } else {
            getIHMTransportServiceApi().cancel(requestId)
        }
    }

    override fun getNetworkType(): Int =
        getIHMTransportServiceApi().getNetworkType() ?: NETWORK_TYPE_NET_UNKNOWN

    // 校验请求结构是否有空值
    private fun validateRequestMap(requestMap: Map<String, Any>): Boolean {
        val logTag = requestMap[REQUEST_TAG]
        for ((key, value) in requestMap) {
            if (value is String && value.isEmpty()) {
                logI("[${logTag}] requestMap $key is empty")
                return false
            }
        }
        return true
    }

    // 构造跨端请求结构
    private fun createPostRequest(kmmRequest: VBTransportPostRequest): MutableMap<String, Any> {
        val requestMap = mutableMapOf<String, Any>()
        val logTag = kmmRequest.logTag
        requestMap[REQUEST_PROTOCOLTYPE] = PROTOCOL_TYPE_HTTP2
        requestMap[REQUEST_ADDRESS] = kmmRequest.url
        requestMap[REQUEST_ID] = kmmRequest.requestId
        // 优先使用header中声明的Content-Type
        if (kmmRequest.header.contains(KMM_REQUEST_CONTENT_TYPE)) {
            requestMap[REQUEST_CONTENT_TYPE] = kmmRequest.header.getValue(KMM_REQUEST_CONTENT_TYPE)
        }
        // 添加默认的 Content-Type
        else {
            requestMap[REQUEST_CONTENT_TYPE] = VBTransportContentType.BYTE.toString()
            kmmRequest.header[KMM_REQUEST_CONTENT_TYPE] = VBTransportContentType.BYTE.toString()
        }
        if (kmmRequest.isDataInitialize()) {
            var data = kmmRequest.data
            if (requestMap[REQUEST_CONTENT_TYPE] == VBTransportContentType.BYTE.toString()) {
                // Any 转 ByteArray
                data = data as ByteArray
                requestMap[REQUEST_DATA] = convertByteArrayToInt8Array(data)
            } else {
                requestMap[REQUEST_DATA] = kmmRequest.data
            }
        } else {
            logI("[${logTag}] createPostRequest: data is empty!")
        }

        requestMap[REQUEST_TAG] = kmmRequest.logTag

        // header map 转 json string
        requestMap[REQUEST_HEADER] = convertHeaderMap2JsonString(kmmRequest.header)
        // 校验是否有Key为空
        validateRequestMap(requestMap)

        return requestMap
    }

    private fun createGetRequest(kmmRequest: VBTransportGetRequest): MutableMap<String, Any> {
        val requestMap = mutableMapOf<String, Any>()
        requestMap[REQUEST_ID] = kmmRequest.requestId
        requestMap[REQUEST_PROTOCOLTYPE] = PROTOCOL_TYPE_HTTP2
        requestMap[REQUEST_ADDRESS] = kmmRequest.url
        requestMap[REQUEST_TAG] = kmmRequest.logTag
        // 优先使用header中声明的contentType
        if (kmmRequest.header.contains(KMM_REQUEST_CONTENT_TYPE)) {
            requestMap[REQUEST_CONTENT_TYPE] =
                kmmRequest.header.getValue(KMM_REQUEST_CONTENT_TYPE).toString()
        } else {
            requestMap[REQUEST_CONTENT_TYPE] = VBTransportContentType.BYTE.toString()
            kmmRequest.header[KMM_REQUEST_CONTENT_TYPE] = VBTransportContentType.BYTE.toString()
        }
        // header map 转 json string
        requestMap[REQUEST_HEADER] = convertHeaderMap2JsonString(kmmRequest.header)
        // 校验是否有Key为空
        validateRequestMap(requestMap)
        return requestMap
    }

    // 将响应值转为对应的contentType用于日志排查
    private fun convertToContentType(
        hmResponse: Map<String, Any?>,
        request: VBTransportBaseRequest
    ): Any? {
        val data = hmResponse[RESPONSE_DATA] ?: ""
        return when (request.header[KMM_REQUEST_CONTENT_TYPE]) {
            VBTransportContentType.JSON.toString() -> {
                data as String
                data
            }

            else -> {
                var rawBytes: ByteArray? = null
                data.let { hmResponseData ->
                    val responseData = hmResponseData as ArrayBuffer
                    rawBytes = responseData.getByteArray()
                }
                rawBytes
            }
        }
    }

    private fun createStringRequest(kmmRequest: VBTransportStringRequest): Map<String, Any> {
        val requestMap = mutableMapOf<String, Any>()
        requestMap[REQUEST_ID] = kmmRequest.requestId
        requestMap[REQUEST_PROTOCOLTYPE] = PROTOCOL_TYPE_HTTP2
        requestMap[REQUEST_ADDRESS] = kmmRequest.url
        requestMap[REQUEST_TAG] = kmmRequest.logTag
        // 添加默认的 Content-Type
        if (kmmRequest.header.contains(KMM_REQUEST_CONTENT_TYPE)) {
            requestMap[REQUEST_CONTENT_TYPE] = kmmRequest.header.getValue(KMM_REQUEST_CONTENT_TYPE)
        } else {
            kmmRequest.header[KMM_REQUEST_CONTENT_TYPE] = VBTransportContentType.JSON.toString()
            requestMap[REQUEST_CONTENT_TYPE] = VBTransportContentType.JSON.toString()
        }
        // header map 转 json string
        requestMap[REQUEST_HEADER] = convertHeaderMap2JsonString(kmmRequest.header)
        return requestMap
    }

    // 构造跨端请求结构
    private fun createHmBytesRequest(kmmRequest: VBTransportBytesRequest): Map<String, Any> {
        val requestMap = mutableMapOf<String, Any>()
        val logTag = kmmRequest.logTag
        requestMap[REQUEST_PROTOCOLTYPE] = PROTOCOL_TYPE_HTTP2
        requestMap[REQUEST_ADDRESS] = kmmRequest.url
        requestMap[REQUEST_ID] = kmmRequest.requestId
        val data = kmmRequest.data ?: ByteArray(0)
        if (data.isNotEmpty()) {
//             ByteArray 转 Int8Array
            requestMap[REQUEST_DATA] = convertByteArrayToInt8Array(data)
        } else {
            logI("[${logTag}] createHmBytesRequest: data is empty!")
        }
        requestMap[REQUEST_TAG] = kmmRequest.logTag
        // 添加默认的 Content-Type
        if (kmmRequest.header.contains(KMM_REQUEST_CONTENT_TYPE)) {
            requestMap[REQUEST_CONTENT_TYPE] = kmmRequest.header.getValue(KMM_REQUEST_CONTENT_TYPE)
        } else {
            requestMap[REQUEST_CONTENT_TYPE] = VBTransportContentType.BYTE.toString()
            kmmRequest.header[KMM_REQUEST_CONTENT_TYPE] = VBTransportContentType.BYTE.toString()
        }
        // header map 转 json string
        requestMap[REQUEST_HEADER] = convertHeaderMap2JsonString(kmmRequest.header)
        return requestMap
    }

    // header map 转 json 字符串
    private fun convertHeaderMap2JsonString(header: Map<String, String>): String {
        return mutableMapOf<String, JsonPrimitive>().also { map ->
            header.entries.forEach {
                map[it.key] = JsonPrimitive(it.value)
            }
        }.encodeToJsonString()
    }

    private fun convertToDouble(value: Any): Double? {
        return when (value) {
            is Double -> value
            is Int -> value.toDouble()
            is String -> value.toDoubleOrNull()
            else -> null
        }
    }

    // ByteArray 转 Int8Array
    private fun convertByteArrayToInt8Array(data: ByteArray): ArrayBuffer {
        val buffer = nativeHeap.allocArray<int8_tVar>(data.size)
        data.usePinned { pinnedData ->
            memcpy(buffer, pinnedData.addressOf(0), data.size.convert())
        }
        return ArrayBuffer(
            buffer.reinterpret(),
            data.size.toLong(),
            type = napi_typedarray_type.napi_int8_array
        )
    }


    // 根据hm返回对象创建PB响应结构
    private fun <RESPONSE : Message<*, *>> createPBResponse(
        errorInfo: Map<String, Any?>,
        hmResponse: Map<String, Any?>?
    ): VBPBResponse<RESPONSE> {
        // 构造KMM层Response对象
        val errorCode = (convertToDouble(errorInfo[RESPONSE_CODE] ?: -1))?.toInt()
        val errorMessage = (errorInfo[RESPONSE_MESSAGE] ?: "") as String
        val kmmResponse = VBPBResponse<RESPONSE>()
        kmmResponse.errorCode = errorCode ?: -1
        kmmResponse.errorMessage = errorMessage
        // 从KNOI获取到字节数组转回Kotlin的ByteArray
        var rawBytes: ByteArray? = null
        if (hmResponse?.get(RESPONSE_DATA) != null) {
            hmResponse[RESPONSE_DATA]?.let { hmResponseData ->
                val responseData = hmResponseData as ArrayBuffer
                rawBytes = responseData.getByteArray()
            }
            kmmResponse.rawBytes = rawBytes
        } else {
            kmmResponse.rawBytes = null
        }
        return kmmResponse
    }

    // 更新上报信息
    private fun getTransportReportInfo(hmResponse: Map<String, Any?>): VBTransportReportInfo {
        return hmResponse["reportInfo"]?.let { hmReportInfo ->
            val hmReportMap = hmReportInfo as Map<*, *>
            logI("receive report info:${hmReportInfo}")
            val code = (hmReportMap[RESPONSE_CODE] as Double).toString()
            val message = (hmReportMap[RESPONSE_MESSAGE] ?: "") as String
            val address = (hmReportMap[RESPONSE_ADDRESS] ?: "") as String
            val dnsTiming = ((hmReportMap[RESPONSE_DNSTIMING] ?: -1) as Double).toString()
            val tcpTiming = ((hmReportMap[RESPONSE_TCPTIMING] ?: -1) as Double).toString()
            val tlsTiming = ((hmReportMap[RESPONSE_TLSTIMING] ?: -1) as Double).toString()
            val totalTiming = ((hmReportMap[RESPONSE_TOTALTIMING] ?: -1) as Double).toString()

            VBTransportReportInfo().apply {
                errorCode = code
                errorMessage = message
                domain = address
                dnsCost = dnsTiming
                connCost = tcpTiming
                sslCost = tlsTiming
                totalCost = totalTiming
            }
        } ?: VBTransportReportInfo()
    }

    // PB请求对象转HM请求对象
    private fun <REQUEST : Message<*, *>, RESPONSE : Message<*, *>> createHmPBRequest(
        kmmRequest: VBPBRequest<REQUEST, RESPONSE>
    ): Map<String, Any> {
        val requestMap = mutableMapOf<String, Any>()
        requestMap[REQUEST_ID] = kmmRequest.requestId
        requestMap[REQUEST_PROTOCOLTYPE] = getProtocolType(kmmRequest.requestConfig)
        requestMap[REQUEST_ADDRESS] = kmmRequest.requestConfig.url
        kmmRequest.requestConfig.httpHeaderMap?.let {
            requestMap[REQUEST_HEADER] = convertHeaderMap2JsonString(it)
        }
        val data = kmmRequest.requestData ?: ByteArray(0)
        // ByteArray 转 Int8Array
        requestMap[REQUEST_DATA] = convertByteArrayToInt8Array(data)
        requestMap[REQUEST_CONTENT_TYPE] = VBTransportContentType.BYTE.toString()
        requestMap[REQUEST_TAG] = kmmRequest.logTag ?: ""
        requestMap[REQUEST_CONN_TIMEOUT] = kmmRequest.requestConfig.connTimeOut
        requestMap[REQUEST_WRITE_TIMEOUT] = kmmRequest.requestConfig.writeTimeOut
        requestMap[REQUEST_READ_TIMEOUT] = kmmRequest.requestConfig.readTimeOut
        return requestMap
    }

    private fun updateResponse(
        errorInfo: Map<String, Any?>,
        hmResponse: Map<String, Any?>,
        request: VBTransportBaseRequest,
        response: VBTransportBaseResponse
    ) {
        val errorCode = ((errorInfo[RESPONSE_CODE] ?: -1) as Double).toInt()
        val errorMessage = (errorInfo[RESPONSE_MESSAGE] ?: "") as String
        val data = convertToContentType(hmResponse, request)
        response.errorCode = errorCode
        response.errorMessage = errorMessage
        // ArkTs Header信息转 KMM Header 信息
        val responseHeader = hmResponse[RESPONSE_HEADERS] as? Map<String, Any>
        responseHeader?.let { headers ->
            response.header = convertMap(responseHeader)
            if (headers.containsKey(HEADER_LOCATION)) {
                headers[HEADER_LOCATION]?.let {
                    val redirectUrl = getRedirectedUrl(request.url, it.toString())
                    logI("redirect url, old: ${request.url}, new: ${redirectUrl}")
                    request.url = redirectUrl
                }
            }
        }
        when (response) {
            is VBTransportPostResponse -> {
                response.data = data
                response.request = request as VBTransportPostRequest
            }

            is VBTransportGetResponse -> {
                response.data = data
                response.request = request as VBTransportGetRequest
            }

            is VBTransportBytesResponse -> {
                data?.let { response.data = data as ByteArray }
                response.request = request as VBTransportBytesRequest
            }

            is VBTransportStringResponse -> {
                data?.let { response.data = data as String }
                response.request = request as VBTransportStringRequest
            }
        }
    }

    // 获取协议类型
    private fun getProtocolType(requestConfig: VBPBRequestConfig?): Int {
        return when (requestConfig?.protocolType ?: VBPBProtocolType.HTTP) {
            VBPBProtocolType.HTTP -> PROTOCOL_TYPE_HTTP2
            VBPBProtocolType.QUIC -> PROTOCOL_TYPE_HTTP3
            else -> PROTOCOL_TYPE_HTTP2
        }
    }

    // 请求url校验
    private fun <R : Message<*, *>, T : Message<*, *>> checkRequestUrl(kmmRequest: VBPBRequest<R, T>) {
        if (kmmRequest.requestConfig.url.isEmpty()) {
            val isHttps = kmmRequest.requestConfig.isHttps
            val scheme = if (isHttps) "https://" else "http://"
            // Android 主端默认主域名
            val domain = kmmRequest.requestConfig.domain
            kmmRequest.requestConfig.url = "$scheme$domain"
        }
    }

    private fun checkResult(resultArray: Array<JSValue?>) {
        // 响应数据 Map
        if (resultArray.isEmpty()) {
            throw ResultArrayException("responseData array is empty")
        }
        val firstElement =
            resultArray[0] ?: throw ResultArrayException("responseData array is null")
        if (firstElement.isNull()) {
            throw ResultArrayException("responseData array is null")
        }

        if (firstElement.isUndefined() || firstElement.isNull()) {
            throw ResultArrayException("responseData array is not a Map")
        }
    }

    private fun logI(content: String) {
        VBPBLog.i(VBPBLog.HMTRANSPORTIMPL, content)
    }

    private fun convertMap(input: Map<String, Any>): Map<String, List<String>> {
        val output = mutableMapOf<String, List<String>>()
        for ((key, value) in input) {
            // 使用正则表达式来匹配不在方括号内的逗号
            val regex = Regex("""(,)(?=(?:[^\[\]]*\[[^\[\]]*\])*[^\[\]]*$)""")
            // 使用正则表达式分割字符串
            val list = value.toString().split(regex).map { it.trim() }
            output[key] = list
        }
        return output
    }
}

actual fun getIVBTransportService(): IVBTransportService {
    if (hmTransportImpl == null) {
        hmTransportImpl = HmTransportImpl()
    }
    return hmTransportImpl as IVBTransportService
}