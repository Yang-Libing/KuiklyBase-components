package com.tencent.qqlive.kmm.sseservice.impl.internal

/**
 * Copyright (c) 2025 Tencent. All rights reserved
 *
 * SSE 请求任务状态
 *
 * @author berryyang
 * @date 2025/4/15 20:17
 */
enum class VBSSERequestState {
    Create, Running, Canceled, Done, Unknown
}