package com.tencent.qqlive.kmm.vbpbservice.export

import com.squareup.wire.kmm.Message
import com.tencent.qqlive.protocol.vb.pb.kmm.ResponseHead
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 响应封装类
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBResponse")
class VBPBResponse<R : Message<*, *>> {

    // 响应的PB Message 对象
    @ObjCName("message")
    var message: R? = null

    // 响应的原始数据
    @ObjCName("rawBytes")
    var rawBytes: ByteArray? = null

    // 响应头
    @ObjCName("responseHead")
    var responseHead: ResponseHead? = null

    // 结果码
    @ObjCName("errorCode")
    var errorCode = 0

    // 错误类型
    @ObjCName("errorCodeType")
    var errorCodeType: String? = null

    // 错误描述信息
    @ObjCName("errorMessage")
    var errorMessage: String? = null

    // 上报信息
    @ObjCName("transportReportInfo")
    var transportReportInfo: VBPBReportInfo? = null
}