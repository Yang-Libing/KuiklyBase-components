package com.tencent.qqlive.kmm.sseservice

import com.tencent.qqlive.kmm.sseservice.export.IVBSSEListener
import com.tencent.qqlive.kmm.sseservice.export.VBSSERequest
import com.tencent.qqlive.kmm.sseservice.export.VBSSEResultCode
import com.tencent.qqlive.kmm.sseservice.impl.internal.VBSSERequestManager
import com.tencent.qqlive.kmm.sseservice.impl.internal.VBSSERequestState
import com.tencent.qqlive.kmm.sseservice.impl.internal.VBSSETask
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBLog
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBLog.SSE_TASK_MANAGER
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBRequestIdGenerator
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.getTimestamp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object VBSSEService {
    private val networkScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val taskManager = VBSSERequestManager

    fun send(request: VBSSERequest, listener: IVBSSEListener): Int {
        val startTs = getTimestamp()
        // 生成请求ID
        request.requestId = VBPBRequestIdGenerator.getRequestId()
        val task = VBSSETask(request.requestId, request.logTag, taskManager)
        taskManager.onTaskBegin(task)
        networkScope.launch {
            VBPBLog.i(SSE_TASK_MANAGER, "[${request.logTag}] execute task in coroutine, " +
                    "requestId: ${request.requestId}")
            task.send(request, object : IVBSSEListener {
                override fun onOpen() {
                    listener.onOpen()
                }

                override fun onEvent(id: String?, event: String?, data: String) {
                    listener.onEvent(id, event, data)
                }

                override fun onClosed() {
                    if (task.getState() != VBSSERequestState.Done) {
                        task.setState(VBSSERequestState.Done)
                        listener.onClosed()
                    }
                }

                override fun onFailure(code: Int, message: String) {
                    if (task.getState() != VBSSERequestState.Done) {
                        task.setState(VBSSERequestState.Done)
                        listener.onFailure(code, message)
                    }
                }
            })
        }

        checkTaskWithTimeout(request.logTag, request.totalTimeout, request.requestId) {
            VBPBLog.i(SSE_TASK_MANAGER, "[${request.logTag}] task failed because " +
                    "timeout(${request.totalTimeout}) reached, requestId: ${request.requestId}")
            listener.onFailure(VBSSEResultCode.CODE_FORCE_TIMEOUT, "请求超时")
        }
        return request.requestId
    }

    fun cancel(requestId: Int) {
        taskManager.cancel(requestId)
    }

    private fun checkTaskWithTimeout(
        logTag: String,
        timeout: Long,
        requestId: Int,
        block: () -> Unit
    ) {
        networkScope.launch {
            VBPBLog.i(SSE_TASK_MANAGER, "[${logTag}] checkTaskWithTimeout: $timeout, " +
                    "requestId: $requestId")
            if (timeout <= 0) return@launch
            delay(timeout)
            taskManager.getTask(requestId)?.let { task ->
                if (task.getState() != VBSSERequestState.Done) {
                    task.setState(VBSSERequestState.Done)
                    block()
                }
            }
        }
    }
}
