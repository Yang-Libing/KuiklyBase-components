package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.squareup.wire.kmm.Message
import com.tencent.qqlive.protocol.vb.pb.kmm.ResponseHead
import okio.ByteString

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB请求Message解包结果
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
class VBPBUnPackageMessageResult<T : Message<*, *>> {
    var responseData: ByteArray? = null // 原始网络响应数据
    var responseMessage: T? = null // 响应Message
    var responseHead: ResponseHead? = null // Header
    var errorCode = 0 // 错误码
    var errorCodeType: String = "" // 错误码类型
    var errorMessage: String = "" // 异常消息
    var transInfo: Map<String, ByteString>? = null // trpc头中的透传字段
    var blockIntervalTime = 0 // 后端限流时间间隔
    var needRetryFlag = false // 后端下发的自动重试标识
}