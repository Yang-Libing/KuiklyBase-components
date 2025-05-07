package com.tencent.tmm.pbservice

import com.squareup.wire.kmm.Message

interface VBPBServiceListener<R : Message<*, *>?, T : Message<*, *>?> {
    /**
     * 请求成功回调函数
     *
     * @param requestId 请求id
     * @param request   请求结构体
     * @param response  响应结构体
     */
    fun onSuccess(requestId: Int, request: R?, response: T?)

    /**
     * 请求失败回调函数
     *
     * @param requestId 请求id
     * @param errorCode 失败错误码
     * @param request   请求结构体
     * @param response  失败结构体
     * @param exception 失败异常信息
     */
    fun onFailure(requestId: Int, errorCode: Int, request: R?, response: T?, exception: Throwable?)
}