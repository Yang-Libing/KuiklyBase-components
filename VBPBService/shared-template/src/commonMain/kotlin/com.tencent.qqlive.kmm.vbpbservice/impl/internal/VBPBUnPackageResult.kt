package com.tencent.qqlive.kmm.vbpbservice.impl.internal

import com.tencent.qqlive.protocol.vb.pb.kmm.ResponseHead
import okio.ByteString

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB解包结果
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
class VBPBUnPackageResult {
    var messageBytes: ByteArray? = null // 解包数据
    var errorCode = 0 // 打包错误码
    var businessCode = 0 // 打包错误码
    var errorCodeType: String = "" // 错误码类型
    var errorMessage: String = "" // 错误描述
    var needRetryFlag = false // 请求重试标记
    var responseHead: ResponseHead? = null // 响应头信息
    var blockIntervalTime = -1 // 后端限流时间间隔
    var transInfo: MutableMap<String, ByteString>? = null // Trpc头中的透传字段
}