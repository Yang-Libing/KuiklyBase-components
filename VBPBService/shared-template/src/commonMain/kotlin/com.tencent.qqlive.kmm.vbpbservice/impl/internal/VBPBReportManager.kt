package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import co.touchlab.stately.collections.ConcurrentMutableMap
import com.tencent.qqlive.kmm.vbpbservice.export.VBPBReportInfo
import com.tencent.qqlive.kmm.vbpbservice.export.VBTransportReportInfo

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 上报管理
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
object VBPBReportManager {

    val reportInfoMap = ConcurrentMutableMap<Int, VBPBReportInfo>()

    // 添加上报对象
    fun addReportInfo(requestId: Int, logTag: String, startTs: Long) {
        val reportInfo = VBPBReportInfo()
        reportInfo.startTs = startTs
        reportInfo.logTag = logTag
        VBPBPersonalize.getBucketInfo()?.let {
            reportInfo.bucketId = it.mBucketId
        }
        reportInfo.requestId = requestId
        reportInfoMap[requestId] = reportInfo
    }

    // 移除上报对象
    fun removeReportInfo(requestId: Int) {
        reportInfoMap.remove(requestId)
    }

    // 获取统计信息
    fun getReportInfo(requestId: Int): VBPBReportInfo? = reportInfoMap[requestId]

    // 限流类型
    fun setLimitType(requestId: Int, limitType: Int) {
        getReportInfo(requestId)?.limitType = limitType
    }

    // 协议类型
    fun setBusiDataType(requestId: Int, busiDataType: Int) {
        getReportInfo(requestId)?.busiDataType = busiDataType
    }

    // 设置 Header打包耗时
    fun setPackageHeaderTimeSpent(requestId: Int, startTs: Long) {
        getReportInfo(requestId)?.packageHeaderCost = getTimestamp() - startTs
    }

    // 设置 PB帧协议打包耗时
    fun setPackagePBFrameTimeSpent(requestId: Int, startTs: Long) {
        getReportInfo(requestId)?.packagePBFrameCost = getTimestamp() - startTs
    }

    // 获取 打包整体耗时
    fun setPackageTimeSpent(requestId: Int, startTs: Long) {
        getReportInfo(requestId)?.packageCost = getTimestamp() - startTs
    }

    // 设置 Header解包耗时
    fun setUnpackageHeaderTimeSpent(requestId: Int, startTs: Long) {
        getReportInfo(requestId)?.unpackageHeaderCost = getTimestamp() - startTs
    }

    // 设置 PB协议帧解包耗时
    fun setUnpackagePBFrameTimeSpent(requestId: Int, startTs: Long) {
        getReportInfo(requestId)?.unpackagePBFrameCost = getTimestamp() - startTs
    }

    // 设置 解包整体耗时
    fun setUnpackageTimeSpent(requestId: Int, startTs: Long) {
        getReportInfo(requestId)?.unpackageCost = getTimestamp() - startTs
    }

    // 设置 QmfCmd Id
    fun setQmfCmdId(requestId: Int, cmdId: String?) {
        getReportInfo(requestId)?.qmfCmdId = cmdId
    }

    // 设置 PBCmd Id
    fun setPBCmdId(requestId: Int, cmdId: String?) {
        getReportInfo(requestId)?.pbCmdId = cmdId
    }

    // 设置 Callee
    fun setCallee(requestId: Int, callee: String) {
        getReportInfo(requestId)?.callee = callee
    }

    // 设置 Func
    fun setFunc(requestId: Int, func: String) {
        getReportInfo(requestId)?.func = func
    }

    //  设置业务场景标识 scene
    fun setServiceTag(requestId: Int, serviceTag: String?) {
        getReportInfo(requestId)?.serviceTag = serviceTag
    }

    //  设置错误码
    fun setError(requestId: Int, errorCode: Int, errorType: String?, errorMessage: String?) {
        getReportInfo(requestId)?.let {
            it.errorCode = errorCode
            it.errorType = errorType
            it.errorMessage = errorMessage
        }
    }

    //  设置业务错误码
    fun setBusinessError(
        requestId: Int,
        businessErrorCode: Int,
        businessErrorType: String?,
        businessErrorMessage: String?
    ) {
        getReportInfo(requestId)?.let {
            it.businessErrorCode = businessErrorCode
            it.businessErrorType = businessErrorType
            it.businessErrorMessage = businessErrorMessage
        }
    }

    // 设置 请求数据包长度
    fun setRequestPackageLength(requestId: Int, requestPackageLength: Long) {
        getReportInfo(requestId)?.requestPackageLength = requestPackageLength
    }

    // 设置 响应包长度
    fun setResponsePackageLength(requestId: Int, responsePackageLength: Long) {
        getReportInfo(requestId)?.responsePackageLength = responsePackageLength
    }

    // 设置 分桶id
    fun setBucketId(requestId: Int, bucketId: Int) {
        getReportInfo(requestId)?.bucketId = bucketId
    }

    // 设置 总耗时
    fun setTotalTimeSpent(requestId: Int) {
        getReportInfo(requestId)?.let {
            it.totalCost = getTimestamp() - it.startTs
        }
    }

    // 设置 页面参数
    fun setPageParams(requestId: Int, pageParams: Map<String, String>?) {
        getReportInfo(requestId)?.pageParams = pageParams
    }

    // 设置服务端耗时
    fun setServerRspCost(requestId: Int, serverRspCost: Long) {
        getReportInfo(requestId)?.serverRspCost = serverRspCost
    }

    // 设置重试标记
    fun setAutoRetryFlag(requestId: Int, autoRetryFlag: Int) {
        getReportInfo(requestId)?.autoRetryFlag = autoRetryFlag
    }

    // 设置是否可以重试标记
    fun setNeedRetryFlag(requestId: Int, needRetryFlag: Boolean) {
        getReportInfo(requestId)?.needRetryFlag = needRetryFlag
    }

    // 设置传输阶段上报信息
    fun setTransportReportInfo(requestId: Int, transportReportInfo: VBTransportReportInfo) {
        getReportInfo(requestId)?.transportReportInfo = transportReportInfo
    }

    fun setDiscontentReasons(requestId: Int, list: List<String>?) {
        if (!list.isNullOrEmpty()) {
            getReportInfo(requestId)?.discontentReason = list.joinToString("$")
        }
    }

    fun setNetType(requestId: Int, netType: Int) {
        getReportInfo(requestId)?.netType = netType
    }

    // 设置 是否请求走 libcurl 标识. 0: 没走 1: 走
    fun setUseCurl(requestId: Int, useCurl: String) {
        getReportInfo(requestId)?.transportReportInfo?.useCurl = useCurl
    }
}