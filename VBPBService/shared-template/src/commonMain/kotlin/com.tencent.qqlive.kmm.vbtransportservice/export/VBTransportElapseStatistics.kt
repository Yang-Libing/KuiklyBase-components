package com.tencent.qqlive.kmm.vbtransportservice.export

/**
 * Copyright (c) 2025 Tencent. All rights reserved
 *
 * 网络请求响应耗时统计
 *
 * @author berryyang
 * @date 2025/3/3 10:02
 */
data class VBTransportElapseStatistics(
    /**
     * DNS 解析耗时
     */
    var nameLookupTimeMs: Double = 0.0,
    /**
     * 连接耗时
     */
    var connectTimeMs: Double = 0.0,
    /**
     * https ssl 握手耗时
     */
    var sslCostTimeMs: Double = 0.0,
    /**
     * 传输准备耗时, 例如发送 HTTP 请求头前的处理时间
     */
    var preTransferTime: Double = 0.0,
    /**
     * 首字节到达耗时（TTFB）
     * 统计的是 数据发送耗时 和 数据发送完毕到首字节返回耗时 之和
     */
    var startTransferTimeMs: Double = 0.0,
    /**
     * 所有重定向过程的总耗时
     */
    var redirectTime: Double = 0.0,
    /**
     * 数据接收耗时
     */
    var recvTime: Double = 0.0,
    /**
     * 整个请求的总耗时
     */
    var totalTimeMs: Double = 0.0,
)
