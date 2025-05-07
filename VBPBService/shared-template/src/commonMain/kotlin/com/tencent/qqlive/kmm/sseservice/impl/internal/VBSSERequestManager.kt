package com.tencent.qqlive.kmm.sseservice.impl.internal

import co.touchlab.stately.collections.ConcurrentMutableMap
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBLog

/**
 * Copyright (c) 2025 Tencent. All rights reserved
 *
 * SSE 网络请求任务管理器
 *
 * @author berryyang
 * @date 2025/4/15 20:09
 */
object VBSSERequestManager {
    // 任务状态管理Map
    private val taskMap = ConcurrentMutableMap<Int, VBSSETask>()

    fun getTask(requestId: Int): VBSSETask? = taskMap[requestId]

    fun onTaskBegin(task: VBSSETask) {
        logI("${task.logTag} onTaskBegin() requestId: ${task.requestId}")
        taskMap[task.requestId] = task
    }

    fun getState(requestId: Int): VBSSERequestState {
        if (!taskMap.containsKey(requestId)) {
            logI("requestId: $requestId don't exist！")
            return VBSSERequestState.Unknown
        }
        return taskMap[requestId]?.getState() ?: VBSSERequestState.Unknown
    }

    fun cancel(requestId: Int) {
        taskMap[requestId]?.cancel()
        logI("requestId: $requestId is cancelled!")
        onTaskFinish(requestId)
    }

    fun onTaskFinish(requestId: Int) {
        taskMap.remove(requestId)
        logI("requestId: $requestId is removed!")
    }

    private fun logI(content: String) {
        VBPBLog.i(VBPBLog.SSE_TASK_MANAGER, content)
    }
}