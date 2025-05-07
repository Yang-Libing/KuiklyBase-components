package com.tencent.qqlive.kmm.vbpbservice.impl.internal

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 请求辅助工具
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */

/**
 * 获取时间戳，毫秒为单位
 */
fun getTimestamp(): Long = getPlatformTimeStamp()

// 跨端时间戳实现，Android为系统实现，iOS、鸿蒙跨端实现
expect fun getPlatformTimeStamp(): Long