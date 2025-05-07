package com.tencent.qqlive.kmm.vbpbservice.impl.platform

import com.tencent.tmm.knoi.annotation.ServiceConsumer
import com.tencent.tmm.knoi.type.JSValue

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * KN 需要 鸿蒙平台实现的接口声明
 *
 * @author haibarawang
 * @date 2024/4/13 14:41
 */
@ServiceConsumer
interface IHMTransportService {

    // 获取网络类型
    fun getNetworkType(): Int?

    // 设置日志实现，统一KMM和ArkTs日志实现
    fun setLogImpl(logImpl: (Array<JSValue>) -> Unit)

    // 获取时间戳
    fun getTimestamp(): String?

    // 发送 Get 字符串类型 请求
    fun sendGetRequest(requestMap: Map<String, Any?>, callback: (Array<JSValue?>) -> Unit)

    // 发送 Post 字节数组类型 请求
    fun sendPostRequest(requestMap: Map<String, Any?>, callback: (Array<JSValue?>) -> Unit)

    // 取消请求
    fun cancel(requestId: Int)

}