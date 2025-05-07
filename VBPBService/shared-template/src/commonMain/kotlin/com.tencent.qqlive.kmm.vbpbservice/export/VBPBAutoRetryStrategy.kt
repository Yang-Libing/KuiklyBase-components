package com.tencent.qqlive.kmm.vbpbservice.export

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved.
 * 类功能描述:
 * VBPBAutoRetryStrategy 重试策略类
 * VBPBAutoRetryRequestParams 回调请求重试策略接口参数类
 *
 * @author woodyjjli
 * @date 2024/9/2
 */

enum class VBPBAutoRetryErrorCategory(rawValue: Int) {
    // 未知
    UNKNOWN(0),
    // 网络错误
    NET(1),
    // 业务错误
    BIZ(2),
}

@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBAutoRetryStrategy")
data class VBPBAutoRetryStrategy(
    @ObjCName("canRetry")
    val canRetry: Boolean = false, // 标记当前失败的请求是否可以再次重试 默认不可重试
    @ObjCName("autoRetryConfig")
    val autoRetryConfig: String? = null, // 记录当前检查结果对应的重试策略
    @ObjCName("autoRetryDelayMillis")
    val autoRetryDelayMillis: Long = 500L // 延迟重试时间 单位毫秒,默认500L
)

data class VBPBAutoRetrySuccessRequestParams(
    // 当前请求的callee
    val callee: String,
    // 当前请求的func
    val func: String,
    // callee和func相同情况下再按照pageType区分
    val pageType: String? = null,
)

data class VBPBAutoRetryFailureRequestParams(
    // 当前请求的callee
    val callee: String,
    // 当前请求的func
    val func: String,
    // callee和func相同情况下再按照pageType区分
    val pageType: String? = null,
    // 错误类型
    // 1 表示发生了网络通道错误
    // 2 表示发生了业务错误
    val errorCategory: VBPBAutoRetryErrorCategory,
    // 错误码, 存储当前错误的错误码
    val errorCode: Int,
    // 标记业务错误是否可以重试 默认false是不可用重试 true是可以重试
    val isNeedRetryFromServer: Boolean,
    // 标记是否是自动重试的请求
    // 默认0是主动请求
    // 1或2是自动重试请求
    val autoRetryFlag: Int,
)

