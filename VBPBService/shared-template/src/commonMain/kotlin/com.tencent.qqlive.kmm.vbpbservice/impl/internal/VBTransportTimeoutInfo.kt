package com.tencent.qqlive.kmm.vbpbservice.impl.internal
/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB超时信息
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
data class VBTransportTimeoutInfo(
    val readWriteTimeout: Int = 0,
    val connTimeout: Int = 0,
)