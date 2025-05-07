package com.tencent.qqlive.kmm.sseservice.export

/**
 * Copyright (c) 2025 Tencent. All rights reserved
 *
 * SSE 请求结果码
 *
 * @author berryyang
 * @date 2025/4/16 12:50
 */
object VBSSEResultCode {
    const val CODE_OK = 0
    // 任务已被取消
    const val CODE_CANCELED = -10001
    // 强制超时
    const val CODE_FORCE_TIMEOUT = -2001
}