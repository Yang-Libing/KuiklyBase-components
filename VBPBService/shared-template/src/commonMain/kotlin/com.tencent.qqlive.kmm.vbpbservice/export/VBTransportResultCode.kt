package com.tencent.qqlive.kmm.vbpbservice.export

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * 通用请求 结果码
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
object VBTransportResultCode {

    const val CODE_OK = 0
    // 任务已被取消
    const val CODE_CANCELED = -10001
    // 强制超时
    const val CODE_FORCE_TIMEOUT = -2001
}