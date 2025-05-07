package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.squareup.wire.kmm.Message
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBAutoRetryErrorCategory
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBAutoRetryFailureRequestParams
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBAutoRetryFlag
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBAutoRetryStrategy
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBAutoRetrySuccessRequestParams
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBCompletionHandler
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBRequest
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResponse
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResultCode
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBResultCodeType
import com.tencent.qqlive.kmm.vbpbservice.export.VBTransportReportInfo
import com.tencent.qqlive.kmm.vbpbservice.export.VBTransportResultCode
import com.tencent.qqlive.kmm.vbpbservice.impl.platform.getIVBTransportService
import com.tencent.qqlive.kmm.vbpbservice.service.VBPBService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 请求任务
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
// 默认正式环境域名
const val RELEASE_DOMAIN = "a.video.qq.com"
const val EXTRA_REQUEST_HEAD_PAGE_TYPE = "page_type"

class VBPBTask<REQUEST : Message<*, *>, RESPONSE : Message<*, *>>(
    val requestId: Int,
    public val pbRequest: VBPBRequest<REQUEST, RESPONSE>,
    private val packageImpl: VBPBPackage<REQUEST, RESPONSE>,
    private val succHandler: VBPBCompletionHandler<REQUEST, RESPONSE>?,
    private val failHandler: VBPBCompletionHandler<REQUEST, RESPONSE>?,
    val logTag: String,
) {
    private val isAutoRetryEnabled = VBPBAutoRetry.getEnabled()
    private lateinit var packageResult: VBPBPackageMessageResult
    private var state: VBTransportState = VBTransportState.Create
    private var callee: String = pbRequest.callee
    private var func: String = pbRequest.func
    var useCurl: Boolean = pbRequest.useCurl

    fun execute() {
        // 初始化域名
        if (pbRequest.requestConfig.domain.isNullOrEmpty()) {
            val setDomain = VBPBConfig.getDomain()
            if (setDomain.isNullOrEmpty()) {
                pbRequest.requestConfig.domain = RELEASE_DOMAIN
            } else {
                pbRequest.requestConfig.domain = setDomain
            }
        }
        // 环境信息
        val envInfo = VBPBConfig.getEnvInfo()

        logI("execute(),domain:${pbRequest.requestConfig.domain},envInfo:$envInfo")

        // 路由信息
        VBPBReportManager.setCallee(requestId, callee)
        VBPBReportManager.setFunc(requestId, func)
        VBPBReportManager.setServiceTag(requestId, pbRequest.requestConfig.serviceTag)
        // 记录重试标记位
        VBPBReportManager.setAutoRetryFlag(requestId, pbRequest.requestConfig.autoRetryFlag.value)

        state = VBTransportState.Running
        val response = VBPBResponse<RESPONSE>()
        // 取消校验
        if (isCanceled()) {
            logI("execute() request task is canceled")
            response.errorCode = VBPBResultCode.CODE_CANCELED
            response.errorCodeType = VBPBResultCodeType.ERROR_CODE_TYPE_NET_LIB_ERR
            response.errorMessage = "请求已被取消"
            return onBusiFailCallback(pbRequest, response)
        }
        // 参数校验
        if (!checkParam()) {
            response.errorCode = VBPBResultCode.CODE_PBERR_ROUTEERR
            response.errorCodeType = VBPBResultCodeType.ERROR_CODE_TYPE_NET_LIB_ERR
            response.errorMessage = "PB 路由错误，请检查Callee和Func"
            return onBusiFailCallback(pbRequest, response)
        }
        // 默认开启 gzip
        val gzipEnabled = true
        // 协议打包
        packageResult = packageImpl.packageRequest(
            requestId,
            pbRequest.requestMessage,
            callee,
            func,
            pbRequest.requestConfig.serviceTag ?: "",
            pbRequest.requestConfig.extraDataMap ?: mutableMapOf(),
            pbRequest.requestConfig.autoRetryFlag,
            gzipEnabled
        )
        // 取消校验
        if (isCanceled()) {
            response.errorCode = VBPBResultCode.CODE_CANCELED
            response.errorCodeType = VBPBResultCodeType.ERROR_CODE_TYPE_NET_LIB_ERR
            response.errorMessage = "请求已被取消"
            logI("execute() invoke failHandler，task has been canceled")
            return onBusiFailCallback(pbRequest, response)
        }

        // 发送网络请求
        sendRequest(packageResult.packageBytes, gzipEnabled)
    }

    fun onTimeout() {
        val response = VBPBResponse<RESPONSE>()
        if (state != VBTransportState.Done && state != VBTransportState.Canceled) {
            state = VBTransportState.Done
            response.errorCode = VBTransportResultCode.CODE_FORCE_TIMEOUT
            response.errorCodeType = VBPBResultCodeType.ERROR_CODE_TYPE_NET_LIB_ERR
            response.errorMessage = "请求超时"
            onBusiFailCallback(pbRequest, response)
        }
    }

    private fun sendRequest(packageBytes: ByteArray, useGzip: Boolean) {
        val sendLen = packageBytes.size
        logI("execute() invoke network sending interface implemented on various platforms, data size:${sendLen}")
        pbRequest.logTag = logTag
        pbRequest.requestData = packageBytes
        pbRequest.requestConfig.httpHeaderMap = createHttpHeaders(useGzip)
        // 超时配置
        setTimeoutConfig()
        // 桥接发送请求到各个平台
        getIVBTransportService().sendPBRequest(pbRequest, ::onResponse)
    }

    private fun onResponse(
        response: VBPBResponse<RESPONSE>,
        transportReportInfo: VBTransportReportInfo?
    ) {

        logI("onResponse() receive transport response, " +
                "result code:${response.errorCode} , " +
                "message:${response.errorMessage} ," +
                "data size:${response.rawBytes?.size ?: 0} , " +
                "state: $state"
        )
        if (state == VBTransportState.Done) {
            return
        }
        if (state != VBTransportState.Canceled) {
            state = VBTransportState.Done
        }
        // 统计
        transportReportInfo?.let {
            VBPBReportManager.setTransportReportInfo(requestId, it) // 同步传输层上报信息
            // 是否使用 curl 请求
            val useCurl = if (pbRequest.useCurl) "1" else "0"
            VBPBReportManager.setUseCurl(requestId, useCurl)
        }
        VBPBReportManager.setRequestPackageLength(
            requestId,
            pbRequest.requestData?.size?.toLong() ?: 0
        )
        VBPBReportManager.setResponsePackageLength(
            requestId,
            response.rawBytes?.size?.toLong() ?: 0
        )
        VBPBReportManager.setError(
            requestId,
            response.errorCode,
            response.errorCodeType,
            response.errorMessage
        )
        VBPBReportManager.setPageParams(requestId, pbRequest.requestConfig.pageParams)

        // 取消校验
        if (isCanceled()) {
            response.errorCode = VBPBResultCode.CODE_CANCELED
            response.errorCodeType = VBPBResultCodeType.ERROR_CODE_TYPE_NET_LIB_ERR
            response.errorMessage = "请求已被取消"
            return onBusiFailCallback(pbRequest, response)
        }

        if (response.errorCode != VBPBResultCode.CODE_OK) {
            return onBusiRetryCallback(pbRequest, response)
        }

        // 协议解包
        val unpackageResult = packageImpl.unpackageResponse(response.rawBytes)

        // 解包结果
        logI(
            "onResponse() unpackage result code: ${unpackageResult.errorCode}, result code type: ${unpackageResult.errorCodeType} , error message: ${unpackageResult.errorMessage}"
        )

        // 业务回调
        pbRequest.requestHead = packageResult.requestHead
        val pbResponse: VBPBResponse<RESPONSE> = VBPBResponse()
        pbResponse.rawBytes = unpackageResult.responseData
        pbResponse.message = unpackageResult.responseMessage
        pbResponse.responseHead = unpackageResult.responseHead
        pbResponse.errorCode = unpackageResult.errorCode
        pbResponse.errorMessage = unpackageResult.errorMessage
        pbResponse.errorCodeType = unpackageResult.errorCodeType
        VBPBReportManager.setNeedRetryFlag(requestId, unpackageResult.needRetryFlag)
        VBPBReportManager.setDiscontentReasons(
            requestId, unpackageResult.responseHead?.server_report_info
                ?.discontent_reason
        )

        if (pbResponse.errorCode != VBPBResultCode.CODE_OK) {
            return onBusiRetryCallback(pbRequest, pbResponse, unpackageResult.needRetryFlag)
        }

        return onBusiSucCallback(pbRequest, pbResponse)
    }

    private fun onBusiRetryCallback(
        pbRequest: VBPBRequest<REQUEST, RESPONSE>,
        pbResponse: VBPBResponse<RESPONSE>,
        isNeedRetryFromServer: Boolean = false
    ) {
        val resultCode = pbResponse.errorCode
        val resultCodeType =
            pbResponse.errorCodeType ?: VBPBResultCodeType.ERROR_CODE_TYPE_NET_LIB_ERR

        if (!isAutoRetryEnabled ||  // 没有打开自动重试
            pbRequest.requestConfig.autoRetryFlag != VBPBAutoRetryFlag.NO_RETRY ||  // 已经是自动重试请求
            !VBPBAutoRetryUtils.canRetry(resultCode, resultCodeType)
        ) // 错误码和错误类型不支持自动重试
        {
            logI("no auto retry: isAutoRetryEnabled: $isAutoRetryEnabled, autoRetryFlag: ${pbRequest.requestConfig.autoRetryFlag}, resultCode: $resultCode, resultCodeType: $resultCodeType")
            return onBusiFailCallback(pbRequest, pbResponse)
        }

        // 可以重试
        val autoRetryErrorCategory =
            VBPBAutoRetryUtils.getAutoRetryErrorCategory(resultCode, resultCodeType)
        if (autoRetryErrorCategory == VBPBAutoRetryErrorCategory.UNKNOWN) {
            // 理论上这里不可能为 UNKNOWN，如果到了这里，需要检查逻辑
            return onBusiFailCallback(pbRequest, pbResponse)
        }

        val pageType = VBPBAutoRetryUtils.getAutoRetryPageType(pbRequest.requestConfig.pageParams)
        val autoRetryRequestParams = VBPBAutoRetryFailureRequestParams(
            callee = callee,
            func = func,
            pageType = pageType,
            errorCategory = autoRetryErrorCategory,
            errorCode = resultCode,
            isNeedRetryFromServer = isNeedRetryFromServer,
            autoRetryFlag = pbRequest.requestConfig.autoRetryFlag.value
        )
        val pbAutoRetryStrategy: VBPBAutoRetryStrategy? =
            VBPBAutoRetry.onRequestFail(autoRetryRequestParams)
        logI(
            "onResponse() autoRetryRequestParams: $autoRetryRequestParams, canBeRetry:${pbAutoRetryStrategy?.canRetry}"
        )
        if (pbAutoRetryStrategy != null && pbAutoRetryStrategy.canRetry) {
            // 结束本次请求并上报
            finishTaskAndReport(
                pbResponse.errorCode,
                pbResponse.errorCodeType,
                pbResponse.errorMessage
            )
            val autoRetryFlag = when (autoRetryErrorCategory) {
                VBPBAutoRetryErrorCategory.UNKNOWN -> VBPBAutoRetryFlag.NO_RETRY
                VBPBAutoRetryErrorCategory.NET -> VBPBAutoRetryFlag.NETWORK_ERROR_RETRY
                VBPBAutoRetryErrorCategory.BIZ -> VBPBAutoRetryFlag.BUSINESS_ERROR_RETRY
            }
            if (autoRetryFlag != VBPBAutoRetryFlag.NO_RETRY) {
                return sendAutoRetryRequest(
                    pbRequest,
                    pbAutoRetryStrategy,
                    autoRetryFlag
                ) // 结束流程，交给重试
            }
        }

        // 如果最终不能重试，那么回调失败
        return onBusiFailCallback(pbRequest, pbResponse)
    }

    private fun onBusiSucCallback(
        pbRequest: VBPBRequest<REQUEST, RESPONSE>,
        pbResponse: VBPBResponse<RESPONSE>
    ) {
        val pageType = VBPBAutoRetryUtils.getAutoRetryPageType(pbRequest.requestConfig.pageParams)
        val autoRetryRequestParams = VBPBAutoRetrySuccessRequestParams(
            callee = callee,
            func = func,
            pageType = pageType
        )
        VBPBAutoRetry.onRequestSuc(autoRetryRequestParams)
        if (succHandler == null) {
            logI("onResponse() invoke succ handler fail, handler is null")
        } else {
            logI("onResponse() invoke succ handler")
            succHandler.invoke(pbRequest, pbResponse)
        }
        finishTaskAndReport(pbResponse.errorCode, pbResponse.errorCodeType, pbResponse.errorMessage)
    }

    private fun onBusiFailCallback(
        pbRequest: VBPBRequest<REQUEST, RESPONSE>,
        pbResponse: VBPBResponse<RESPONSE>
    ) {
        if (failHandler == null) {
            logI("onResponse() invoke fail handler fail, handler is null")
        } else {
            logI(
                "onResponse() invoke fail handler,error code:${pbResponse.errorCode}" +
                        ", error message:${pbResponse.errorMessage}, error type:${pbResponse.errorCodeType}"
            )
            failHandler.invoke(pbRequest, pbResponse)
        }
        finishTaskAndReport(pbResponse.errorCode, pbResponse.errorCodeType, pbResponse.errorMessage)
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
        report()
        VBPBTaskManager.onTaskFinish(requestId)
    }

    private fun sendAutoRetryRequest(
        pbRequest: VBPBRequest<REQUEST, RESPONSE>,
        autoRetryStrategy: VBPBAutoRetryStrategy,
        autoRetryFlag: VBPBAutoRetryFlag
    ) {
        logI("sendAutoRetryRequest() autoRetryFlag: $autoRetryFlag")
        VBPBTaskManager.getNetworkScope().launch {
            delay(autoRetryStrategy.autoRetryDelayMillis)
            pbRequest.requestConfig.autoRetryFlag = autoRetryFlag
            VBPBService.send(pbRequest, succHandler, failHandler)
        }
    }

    // 注意放在VBPBTaskManager.onTaskFinish前，防止ReportInfo被删除
    private fun report() {
        // 上报
        VBPBReportManager.getReportInfo(requestId)?.let {
            VBPBReport.report(it)
        }
    }

    fun cancel() {
        state = VBTransportState.Canceled
        getIVBTransportService().cancel(requestId, pbRequest.useCurl)
    }

    fun getState(): VBTransportState = this.state

    private fun checkParam(): Boolean = callee.isNotEmpty() && func.isNotEmpty()

    private fun isCanceled() = state == VBTransportState.Canceled

    private fun createHttpHeaders(useGzip: Boolean): Map<String, String> {
        val httpHeaderMap: MutableMap<String, String> =
            pbRequest.requestConfig.httpHeaderMap?.toMutableMap() ?: mutableMapOf()
        if (useGzip) {
            httpHeaderMap["Content-Encoding"] = "gzip"
        }
        pbRequest.requestConfig.serviceTag?.let {
            httpHeaderMap["serviceTag"] = it
        }
        VBPBConfig.getVuid().takeIf { it.isNotEmpty() }?.let {
            httpHeaderMap["loginv"] = it
        }
        VBPBConfig.getQimei36().takeIf { it.isNotEmpty() }?.let {
            httpHeaderMap["uqm"] = it
        }
        logI("req http headers: $httpHeaderMap")
        return httpHeaderMap
    }

    private fun setTimeoutConfig() {
        val networkType = getIVBTransportService().getNetworkType()
        VBPBReportManager.setNetType(requestId, networkType)
        val timeoutInfo = VBTransportTimeoutStrategy.getTimeoutInfo(networkType)
        if (pbRequest.requestConfig.connTimeOut <= 0) {
            pbRequest.requestConfig.connTimeOut = timeoutInfo.connTimeout
        }
        if (pbRequest.requestConfig.readTimeOut <= 0) {
            pbRequest.requestConfig.readTimeOut = timeoutInfo.readWriteTimeout
        }
        if (pbRequest.requestConfig.writeTimeOut <= 0) {
            pbRequest.requestConfig.writeTimeOut = timeoutInfo.readWriteTimeout
        }
    }

    private fun logI(content: String) {
        VBPBLog.i(VBPBLog.PACKAGE_TASK, "$logTag $content")
    }

}