package com.tencent.qqlive.kmm.vbpbservice.export

import com.squareup.wire.kmm.Message
import com.squareup.wire.kmm.ProtoAdapter
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBTab
import com.tencent.qqlive.kmm.vbpbservice.impl.internal.VBPBTab.pbUseCurlConfigKey
import com.tencent.qqlive.protocol.vb.pb.kmm.RequestHead
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 请求封装类
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBRequest")
data class VBPBRequest<RESQUEST : Message<*, *>, RESPONSE : Message<*, *>>(
    // 请求的PB Message对象
    val requestMessage: RESQUEST,
    // 用于解析饭反序列化Response的Adapter对象
    val responseAdapter: ProtoAdapter<RESPONSE>,
    // 请求的callee路由信息
    @ObjCName("callee")
    val callee: String,
    // 请求的func路由信息
    @ObjCName("func")
    val func: String,
) {
    // 请求id(由网络组件内部生成)
    @ObjCName("requestId")
    var requestId: Int = 0

    // 请求头对象
    @ObjCName("requestHead")
    var requestHead: RequestHead? = null

    // 请求打包的原始数据
    @ObjCName("requestData")
    var requestData: ByteArray? = null

    // 请求附加配置
    @ObjCName("requestConfig")
    var requestConfig: VBPBRequestConfig = VBPBRequestConfig()

    // 日志tag
    @ObjCName("logTag")
    var logTag: String? = null

    // 是否使用 libcurl 进行请求
    @ObjCName("useCurl")
    var useCurl: Boolean = VBPBTab.getTabBooleanValue(pbUseCurlConfigKey, false)
}