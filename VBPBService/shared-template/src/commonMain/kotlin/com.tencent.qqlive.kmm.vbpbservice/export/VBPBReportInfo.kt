package com.tencent.qqlive.kmm.vbpbservice.export

/**
 * Copyright (c) 2024 Tencent. All rights reserved.
 *
 * PB网络请求统计信息
 *
 * @author haibarawang
 * @date 2024/3/1
 */

class VBPBReportInfo {
    var startTs: Long = 0 // PBService处理开始时间

    // -------- PB层 统计信息 --------
    var totalCost: Long = 0 // 请求总耗时

    var packageHeaderCost: Long = 0 // Header打包耗时

    var packagePBFrameCost: Long = 0 // PB帧协议打包耗时

    var packageCost: Long = 0 // 打包整体耗时

    var unpackageHeaderCost: Long = 0 // Header解包耗时

    var unpackagePBFrameCost: Long = 0 // PB协议帧解包耗时

    var unpackageCost: Long = 0 // 解包整体耗时

    var qmfCmdId: String? = null // qmf cmd id

    var pbCmdId: String? = null // qmf cmd id

    var callee: String = "" // callee callee和func一定会被传值

    var func: String = "" // func

    var serviceTag: String? = null // scene

    var requestId = 0 // 请求id

    var errorCode = 0 // 错误码

    var errorMessage: String? = null // 错误信息

    var errorType: String? = null // 错误码类型

    var businessErrorCode = 0 // 业务错误码

    var businessErrorMessage: String? = null // 业务错误信息

    var businessErrorType: String? = null // 业务错误码类型

    var requestPackageLength: Long = 0 // 请求数据包长度

    var responsePackageLength: Long = 0 // 响应包长度

    var netType: Int = 0 // 网络类型

    var bucketId = 0 // 分桶id

    var pageParams: Map<String, String>? = null // 页面参数

    var autoRetryFlag: Int = VBPBAutoRetryFlag.NO_RETRY.value // 标记当前请求是否是自动重试的请求

    var needRetryFlag = false // 标记当前请求失败后是否可以重试 默认为false不可以重试

    var busiDataType = 0 // 协议类型：1:qmf，2:trpc

    var serverRspCost: Long = 0 // 业务后台处理耗时

    var discontentReason: String = ""

    // ------- 限流时间 -------
    var limitTime = -1L
    var limitType = 0 // 限流类型：0-不限流，1-ACC限流，2-RS限流
    var logTag = "" // 日志标记

    // ------- 传输层信息集合 -------
    var transportReportInfo: VBTransportReportInfo? = null

}