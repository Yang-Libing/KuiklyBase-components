package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import co.touchlab.stately.collections.ConcurrentMutableMap

class VBTransportManager {

    // 任务状态管理Map
    private val taskMap = ConcurrentMutableMap<Int, VBTransportTask>()

    fun getTask(requestId: Int): VBTransportTask? = taskMap[requestId]

    fun onTaskBegin(task: VBTransportTask) {
        logI("${task.logTag} onTaskBegin() requestId :${task.requestId}")
        taskMap[task.requestId] = task
    }

    fun getState(requestId: Int): VBTransportState {
        if (!taskMap.containsKey(requestId)) {
            logI("requestId:$requestId don't exist！")
            return VBTransportState.Unknown
        }
        return taskMap[requestId]?.getState() ?: VBTransportState.Unknown
    }

    fun cancel(requestId: Int) {
        taskMap[requestId]?.cancel()
        logI("requestId:$requestId is cancelled!")
        onTaskFinish(requestId)
    }

    fun onTaskFinish(requestId: Int) {
        taskMap.remove(requestId)
        logI("requestId:$requestId is removed!")
    }

    private fun logI(content: String) {
        VBPBLog.i(VBPBLog.HMTRANSPORTIMPL, content)
    }
}