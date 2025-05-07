package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import co.touchlab.stately.collections.ConcurrentMutableMap
import com.squareup.wire.kmm.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 请求任务管理
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
object VBPBTaskManager : IVBPBTaskListener {
    private val networkScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    // 任务管理Map
    private val taskMap = ConcurrentMutableMap<Int, VBPBTask<out Message<*, *>, out Message<*, *>>>()

    fun execute(task: VBPBTask<out Message<*, *>, out Message<*, *>>): Int {
        onTaskBegin(task)
        networkScope.launch(track = true) {
            val logTag = VBPBReportManager.getReportInfo(task.requestId)?.logTag
            logI("$logTag execute() coroutine requestId :${task.requestId}")
            task.execute()
        }
        networkScope.launch(track = true) {
            val timeout = task.pbRequest.requestConfig.totalTimeout
            val logTag = VBPBReportManager.getReportInfo(task.requestId)?.logTag
            logI("$logTag execute() coroutine totalTimeout: ${timeout}, " +
                    "requestId: ${task.requestId}")
            if (timeout <= 0) return@launch
            delay(timeout)
            task.onTimeout()
        }
        return task.requestId
    }

    // 取消任务执行
    fun cancel(requestId: Int) {
        taskMap[requestId]?.cancel()
        onTaskFinish(requestId)
    }

    fun getState(requestId: Int): VBTransportState {
        if (!taskMap.containsKey(requestId)) {
            logI("requestId:$requestId don't exist！")
            return VBTransportState.Unknown
        }
        return taskMap[requestId]?.getState() ?: VBTransportState.Unknown
    }

    override fun onTaskBegin(task: VBPBTask<out Message<*, *>, out Message<*, *>>) {
        logI("${task.logTag} onTaskBegin() requestId :${task.requestId}")
        taskMap[task.requestId] = task
    }

    override fun onTaskFinish(requestId: Int) {
        val logTag = VBPBReportManager.getReportInfo(requestId)?.logTag
        logI("$logTag onTaskFinish() requestId :$requestId")
        if (!taskMap.containsKey(requestId)) {
            return
        }
        taskMap.remove(requestId)
        VBPBReportManager.removeReportInfo(requestId)
    }

    private fun logI(content: String) {
        VBPBLog.i(VBPBLog.TASK_MANAGER, content)
    }

    fun getNetworkScope(): CoroutineScope = networkScope
}

