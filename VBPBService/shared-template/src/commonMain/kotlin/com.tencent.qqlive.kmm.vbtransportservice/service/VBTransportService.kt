package com.tencent.qqlive.kmm.vbtransportservice.service

import com.tencent.qqlive.kmm.vbpbservice.export.VBTransportResultCode
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBReportManager
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBRequestIdGenerator
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBTransportManager
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBTransportState
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBTransportTask
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.getTimestamp
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBytesCompletionHandler
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBytesRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBytesResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportGetHandler
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportGetRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportGetResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportPostHandler
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportPostRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportPostResponse
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportStringCompletionHandler
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportStringRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportStringResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * 普通 请求/响应 传输服务
 *
 * @author haibarawang
 * @date 2024/3/13 19:41
 */
object VBTransportService {

    private val networkScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val taskManager = VBTransportManager()

    // 发送字节数组Post类型网络请求
    fun sendBytesRequest(
        request: VBTransportBytesRequest,
        handler: VBTransportBytesCompletionHandler?,
    ) {
        request.requestId = VBPBRequestIdGenerator.getRequestId()
        val startTs = getTimestamp()
        // 上报采集
        VBPBReportManager.addReportInfo(request.requestId, request.logTag, startTs)
        networkScope.launch(track = true) {
            val task = VBTransportTask(request.requestId, request.useCurl, request.logTag, taskManager)
            taskManager.onTaskBegin(task)
            task.sendBytesRequest(request) { response ->
                if (task.getState() != VBTransportState.Done) {
                    task.setState(VBTransportState.Done)
                    handler?.let { it(response) }
                }
            }
        }
        startTimeoutCheckTask(request.totalTimeout, request.requestId) {
            val timeoutResponse = VBTransportBytesResponse().apply {
                this.request = request
                this.errorCode = VBTransportResultCode.CODE_FORCE_TIMEOUT
                this.errorMessage = "请求超时"
            }
            handler?.invoke(timeoutResponse)
        }
    }

    // 发送字符类型Get类型网络请求
    fun sendStringRequest(
        request: VBTransportStringRequest,
        handler: VBTransportStringCompletionHandler?,
    ) {
        request.requestId = VBPBRequestIdGenerator.getRequestId()
        val startTs = getTimestamp()
        // 上报采集
        VBPBReportManager.addReportInfo(request.requestId, request.logTag, startTs)
        networkScope.launch(track = true) {
            val task = VBTransportTask(request.requestId, request.useCurl, request.logTag, taskManager)
            taskManager.onTaskBegin(task)
            task.sendStringRequest(request) { response ->
                if (task.getState() != VBTransportState.Done) {
                    task.setState(VBTransportState.Done)
                    handler?.let { it(response) }
                }
            }
        }
        startTimeoutCheckTask(request.totalTimeout, request.requestId) {
            val timeoutResponse = VBTransportStringResponse().apply {
                this.request = request
                this.errorCode = VBTransportResultCode.CODE_FORCE_TIMEOUT
                this.errorMessage = "请求超时"
            }
            handler?.invoke(timeoutResponse)
        }
    }

    fun sendPostRequest(
        request: VBTransportPostRequest,
        handler: VBTransportPostHandler?
    ) {
        request.requestId = VBPBRequestIdGenerator.getRequestId()
        val startTs = getTimestamp()
        // 上报采集
        VBPBReportManager.addReportInfo(request.requestId, request.logTag, startTs)
        networkScope.launch(track = true) {
            val task = VBTransportTask(request.requestId, request.useCurl, request.logTag, taskManager)
            taskManager.onTaskBegin(task)
            task.sendPostRequest(request) { response ->
                if (task.getState() != VBTransportState.Done) {
                    task.setState(VBTransportState.Done)
                    handler?.let { it(response) }
                }
            }
        }
        startTimeoutCheckTask(request.totalTimeout, request.requestId) {
            val timeoutResponse = VBTransportPostResponse().apply {
                this.request = request
                this.errorCode = VBTransportResultCode.CODE_FORCE_TIMEOUT
                this.errorMessage = "请求超时"
            }
            handler?.invoke(timeoutResponse)
        }
    }

    fun sendGetRequest(
        request: VBTransportGetRequest,
        handler: VBTransportGetHandler?
    ) {
        request.requestId = VBPBRequestIdGenerator.getRequestId()
        val startTs = getTimestamp()
        // 上报采集
        VBPBReportManager.addReportInfo(request.requestId, request.logTag, startTs)
        networkScope.launch(track = true) {
            val task = VBTransportTask(request.requestId, request.useCurl, request.logTag, taskManager)
            taskManager.onTaskBegin(task)
            task.sendGetRequest(request) { response ->
                if (task.getState() != VBTransportState.Done) {
                    task.setState(VBTransportState.Done)
                    handler?.let { it(response) }
                }
            }
        }
        startTimeoutCheckTask(request.totalTimeout, request.requestId) {
            val timeoutResponse = VBTransportGetResponse().apply {
                this.request = request
                this.errorCode = VBTransportResultCode.CODE_FORCE_TIMEOUT
                this.errorMessage = "请求超时"
            }
            handler?.invoke(timeoutResponse)
        }
    }

    private fun startTimeoutCheckTask(timeout: Long, requestId: Int, handlerBlock: () -> Unit) {
        networkScope.launch(track = true) {
            if (timeout <= 0) return@launch
            delay(timeout)
            taskManager.getTask(requestId)?.let { task ->
                if (task.getState() != VBTransportState.Done) {
                    task.setState(VBTransportState.Done)
                    handlerBlock()
                }
            }
        }
    }

    /**
     * 获取http请求状态
     * @param requestId 请求编号
     * @return 请求状态
     *         Create - 已创建
     *         Running - 正在执行
     *         Canceled - 已取消
     *         Done - 已完成
     *         Unknown - 已取消或已完成后删除
     */
    fun getState(requestId: Int): VBTransportState = taskManager.getState(requestId)

    fun cancel(requestId: Int) {
        taskManager.cancel(requestId)
    }
}