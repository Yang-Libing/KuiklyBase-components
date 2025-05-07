package com.tencent.qqlive.kmm.sseservice.impl.internal

import com.tencent.qqlive.kmm.sseservice.export.IVBSSEListener
import com.tencent.qqlive.kmm.sseservice.export.VBSSERequest
import com.tencent.qqlive.kmm.sseservice.export.VBSSEResultCode
import com.tencent.qqlive.kmm.sseservice.impl.platform.getVBSSEService
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBLog

/**
 * Copyright (c) 2025 Tencent. All rights reserved
 *
 * SSE 网络请求任务
 *
 * @author berryyang
 * @date 2025/4/15 20:12
 */
class VBSSETask (
    val requestId: Int,
    val logTag: String,
    private val taskManager: VBSSERequestManager
) {
    private var state: VBSSERequestState = VBSSERequestState.Create

    fun send(request: VBSSERequest, listener: IVBSSEListener) {
        if (isCanceledOrRemoved()) {
            logI("task will callback because task has been canceled.")
            listener.onFailure(VBSSEResultCode.CODE_CANCELED, "请求已被取消")
            return
        }

        state = VBSSERequestState.Running

        // 这里截断listener,便于介入回调过程,处理一些比如统计之类的事情
        getVBSSEService().send(request, object : IVBSSEListener {
            override fun onOpen() {
                logI("request onOpen, requestId: $requestId")
                listener.onOpen()
            }

            override fun onEvent(id: String?, event: String?, data: String) {
                logI("request onEvent, id: $id, type: $event, data: $data, requestId: $requestId")
                listener.onEvent(id, event, data)
            }

            override fun onClosed() {
                logI("request onClosed, requestId: $requestId")
                listener.onClosed()
                taskManager.onTaskFinish(requestId)
            }

            override fun onFailure(code: Int, message: String) {
                logI("request onFailure, code: $code, msg: $message, requestId: $requestId")
                listener.onFailure(code, message)
                taskManager.onTaskFinish(requestId)
            }
        })
    }

    fun getState(): VBSSERequestState = this.state

    fun setState(state: VBSSERequestState) {
        this.state = state
    }

    fun cancel() {
        state = VBSSERequestState.Canceled
        getVBSSEService().cancel(requestId)
    }

    private fun isCanceledOrRemoved(): Boolean =
        (state == VBSSERequestState.Canceled || state == VBSSERequestState.Unknown)

    private fun logI(content: String) {
        VBPBLog.i(VBPBLog.SSE_TASK, "[$logTag] $content")
    }
}