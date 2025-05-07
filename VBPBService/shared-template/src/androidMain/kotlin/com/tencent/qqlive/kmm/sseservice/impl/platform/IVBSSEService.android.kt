package com.tencent.qqlive.kmm.sseservice.impl.platform

import com.tencent.qqlive.kmm.sseservice.export.IVBSSEListener
import com.tencent.qqlive.kmm.sseservice.export.VBSSERequest
import com.tencent.qqlive.modules.vb.transportservice.impl.VBTransportNetwokTypeAssistant
import com.tencent.qqlive.modules.vb.transportservice.impl.VBTransportResultCode
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import java.net.SocketTimeoutException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

object SSEServiceAndroid : IVBSSEService {

    private val factoryMap = ConcurrentHashMap<Long, EventSource.Factory>()
    private val eventSourceMap = ConcurrentHashMap<Int, EventSource>()

    override fun send(request: VBSSERequest, listener: IVBSSEListener) {
        // 拼接基础设置
        val sseRequestBuilder = createSSERequestBuilder()
        // 地址
        sseRequestBuilder.url(request.url)
        // 请求数据
        sseRequestBuilder.post(
            RequestBody.create(
                "application/octet-stream".toMediaTypeOrNull(), request.data
            )
        )
        // 自定义header
        request.headers.forEach {
            sseRequestBuilder.header(it.key, it.value)
        }
        // 任务id
        val taskId = request.requestId
        // 系统回调
        val sseListener: EventSourceListener = object : EventSourceListener() {
            override fun onOpen(eventSource: EventSource, response: Response) {
                super.onOpen(eventSource, response)
                listener.onOpen()
                eventSourceMap[taskId] = eventSource
            }

            override fun onEvent(
                eventSource: EventSource, id: String?, type: String?, data: String
            ) {
                super.onEvent(eventSource, id, type, data)
                listener.onEvent(id, type, data)
            }

            override fun onClosed(eventSource: EventSource) {
                super.onClosed(eventSource)
                listener.onClosed()
                eventSourceMap.remove(taskId)
            }

            override fun onFailure(
                eventSource: EventSource,
                throwable: Throwable?,
                response: Response?
            ) {
                super.onFailure(eventSource, throwable, response)
                val networkState = VBTransportNetwokTypeAssistant.getNetworkState()
                val code = VBTransportResultCode.parseErrorCode(networkState, throwable)
                val desc = VBTransportResultCode.parseErrorDescription(networkState, throwable)
                listener.onFailure(code, desc)
                eventSourceMap.remove(taskId)
            }
        }
        val sseRequest = sseRequestBuilder.build()
        getFactory(request.totalTimeout).newEventSource(sseRequest, sseListener)
    }

    override fun cancel(id: Int) {
        eventSourceMap[id]?.cancel()
    }

    private fun getFactory(timeout: Long): EventSource.Factory {
        return factoryMap.getOrPut(timeout) {
            val builder = OkHttpClient.Builder()
            if (timeout > 0) {
                builder.connectTimeout(timeout, TimeUnit.MILLISECONDS)
                builder.readTimeout(timeout, TimeUnit.MILLISECONDS)
                builder.writeTimeout(timeout, TimeUnit.MILLISECONDS)
            }
            EventSources.createFactory(builder.build())
        }
    }

    private fun createSSERequestBuilder(): Request.Builder {
        return Request.Builder()
            .header("Accept", "text/event-stream")
            .header("Connection", "keep-alive")
            .header("Cache-Control", "no-cache")
    }
}

actual fun getVBSSEService(): IVBSSEService {
    return SSEServiceAndroid
}