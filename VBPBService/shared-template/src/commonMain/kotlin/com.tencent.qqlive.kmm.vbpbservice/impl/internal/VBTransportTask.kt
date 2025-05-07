package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResultCode
import com.tencent.qqlive.kmm.vbpbservice.export.VBTransportReportInfo
import com.tencent.qqlive.kmm.vbpbservice.impl.platform.getIVBTransportService
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBaseRequest
import com.tencent.qqlive.kmm.vbtransportservice.export.VBTransportBaseResponse
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


class VBTransportTask(
    val requestId: Int,
    val useCurl: Boolean,
    val logTag: String,
    private val taskManager: VBTransportManager
) {

    private var state: VBTransportState = VBTransportState.Create

    private fun wrapGetResponse(
        getCallback: ((getResponse: VBTransportGetResponse) -> Unit)?
    ): ((baseResponse: VBTransportBaseResponse) -> Unit)? {
        getCallback ?: return null
        return { response ->
            val res = response as? VBTransportGetResponse
            res?.let { getCallback(it) }
        }
    }

    private fun wrapPostResponse(
        postCallback: ((postResponse: VBTransportPostResponse) -> Unit)?
    ): ((baseResponse: VBTransportBaseResponse) -> Unit)? {
        postCallback ?: return null
        return { response ->
            val res = response as? VBTransportPostResponse
            res?.let { postCallback(it) }
        }
    }

    private fun wrapStringResponse(
        stringCallback: ((stringResponse: VBTransportStringResponse) -> Unit)?
    ): ((baseResponse: VBTransportBaseResponse) -> Unit)? {
        stringCallback ?: return null
        return { response ->
            val res = response as? VBTransportStringResponse
            res?.let { stringCallback(it) }
        }
    }

    private fun wrapBytesResponse(
        bytesCallback: ((bytesResponse: VBTransportBytesResponse) -> Unit)?
    ): ((baseResponse: VBTransportBaseResponse) -> Unit)? {
        bytesCallback ?: return null
        return { response ->
            val res = response as? VBTransportBytesResponse
            res?.let { bytesCallback(it) }
        }
    }

    private fun handleResponse(
        request: VBTransportBaseRequest,
        response: VBTransportBaseResponse,
        transportReportInfo: VBTransportReportInfo?,
        handler: ((response: VBTransportBaseResponse) -> Unit)?
    ) {
        // 统计
        transportReportInfo?.let {
            response.serverIP = it.serverIP ?: ""
            response.serverPort = it.serverPort ?: ""
            VBPBReportManager.setTransportReportInfo(requestId, it) // 同步传输层上报信息
            // 是否使用 curl 请求
            val useCurl = if (request.useCurl) "1" else "0"
            VBPBReportManager.setUseCurl(request.requestId, useCurl)
        }

        handler?.let {
            if (isCanceledOrRemoved()) {
                logI("execute() request task is canceled")
                response.errorCode = VBPBResultCode.CODE_CANCELED
                response.errorMessage = "请求已被取消"
                logI("execute() invoke failHandler，task has been canceled")
                it(response)
                return@let
            }
            it(response)
            taskManager.onTaskFinish(requestId)
        } ?: run {
            logI("handler is null!")
        }

        // TODO: 待完善 businessErrorType 字段获取
        finishTaskAndReport(response.errorCode, null, response.errorMessage)
    }

    fun sendBytesRequest(
        request: VBTransportBytesRequest,
        handler: VBTransportBytesCompletionHandler?,
    ) {
        if (isCanceledOrRemoved()) {
            val response = VBTransportBytesResponse()
            logI("execute() request task is canceled")
            handler?.let {
                response.errorCode = VBPBResultCode.CODE_CANCELED
                response.errorMessage = "请求已被取消"
                logI("execute() invoke failHandler，task has been canceled")
                it(response)
            } ?: run {
                logI("task has been canceled and handler is null!")
            }
            return
        }
        state = VBTransportState.Running
        getIVBTransportService().sendBytesRequest(request) { response, transportReportInfo ->
            handleResponse(request, response, transportReportInfo, wrapBytesResponse(handler))
        }
    }

    private fun finishTaskAndReport(
        businessErrorCode: Int,
        businessErrorType: String?,
        businessErrorMessage: String?
    ) {
        VBPBReportManager.setBusinessError(
            requestId,
            businessErrorCode,
            businessErrorType,
            businessErrorMessage
        )

        VBPBReportManager.setTotalTimeSpent(requestId)
        // 上报
        VBPBReportManager.getReportInfo(requestId)?.let {
            VBPBReport.report(it)
        }
        VBPBTaskManager.onTaskFinish(requestId)
    }

    private fun isCanceledOrRemoved(): Boolean =
        state == VBTransportState.Canceled || state == VBTransportState.Unknown

    // 发送字符类型Get类型网络请求
    fun sendStringRequest(
        request: VBTransportStringRequest,
        handler: VBTransportStringCompletionHandler?,
    ) {
        if (isCanceledOrRemoved()) {
            val response = VBTransportStringResponse()
            logI("execute() request task is canceled")
            handler?.let {
                response.errorCode = VBPBResultCode.CODE_CANCELED
                response.errorMessage = "请求已被取消"
                logI("execute() invoke failHandler，task has been canceled")
                it(response)
            } ?: run {
                logI("task has been canceled and handler is null!")
            }
            return
        }
        state = VBTransportState.Running
        getIVBTransportService().sendStringRequest(request) { response, transportReportInfo ->
            handleResponse(request, response, transportReportInfo, wrapStringResponse(handler))
        }
    }

    fun sendPostRequest(
        request: VBTransportPostRequest,
        handler: VBTransportPostHandler?
    ) {
        if (isCanceledOrRemoved()) {
            val response = VBTransportPostResponse()
            logI("execute() request task is canceled before")
            handler?.let {
                response.errorCode = VBPBResultCode.CODE_CANCELED
                response.errorMessage = "请求已被取消"
                logI("execute() invoke failHandler，task has been canceled")
                it(response)
            } ?: run {
                logI("task has been canceled and handler is null!")
            }
            return
        }
        state = VBTransportState.Running
        getIVBTransportService().post(request) { response, transportReportInfo ->
            handleResponse(request, response, transportReportInfo, wrapPostResponse(handler))
        }
    }

    fun sendGetRequest(
        request: VBTransportGetRequest,
        handler: VBTransportGetHandler?
    ) {
        if (isCanceledOrRemoved()) {
            val response = VBTransportGetResponse()
            logI("execute() request task is canceled before")
            handler?.let {
                response.errorCode = VBPBResultCode.CODE_CANCELED
                response.errorMessage = "请求已被取消"
                logI("execute() invoke failHandler，task has been canceled")
                it(response)
            } ?: run {
                logI("task has been canceled and handler is null!")
            }
            return
        }
        state = VBTransportState.Running
        getIVBTransportService().get(request) { response, transportReportInfo ->
            handleResponse(request, response, transportReportInfo, wrapGetResponse(handler))
        }
    }

    fun getState(): VBTransportState = this.state

    fun setState(state: VBTransportState) {
        this.state = state
    }

    fun cancel() {
        state = VBTransportState.Canceled
        getIVBTransportService().cancel(requestId, useCurl)
    }

    private fun logI(content: String) {
        VBPBLog.i(VBPBLog.PACKAGE_TASK, "$logTag $content")
    }

}