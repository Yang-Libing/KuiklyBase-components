package com.tencent.tmm.pbservice

import com.squareup.wire.kmm.Message
import com.tencent.tmm.pbservice.export.VBPBRequestConfigTMM
import com.tencent.tmm.pbservice.export.VBPBRequestTMM
import com.tencent.tmm.pbservice.export.VBPBResponseTMM
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

typealias SuccessHandler<R,T> = (request: VBPBRequestTMM<R>, response: VBPBResponseTMM<T>) -> Unit
typealias FailHandler<R,T> = (request: VBPBRequestTMM<R>, response: VBPBResponseTMM<T>) -> Unit

@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBServiceTMM")
interface VBPBServiceTMM {
    /**
    发送PB请求,自定义callee和func,如果自定义callee和func,两个参数必须填
    @param message PBRequest
    @param config 当前请求的额外配置
    @param callee 调用的服务名
    @param func 调用的函数
    @param listener 监听者
    @return 返回RequestIdentifier 小于0代表发生了错误
     */
    @ObjCName("sendRequest")
    fun <R : Message<*, *>?, T : Message<*, *>?> sendRequest(
        request: R,
        config: VBPBRequestConfigTMM<T>,
        callee: String,
        func: String,
        successHandler: SuccessHandler<R,T>?,
        failHandler: FailHandler<R,T>?
    ): Long

    /**
    取消请求
    @param requestId  RequestIdentifier
     */
    @ObjCName("cancel")
    fun cancel(requestId: Long)
}