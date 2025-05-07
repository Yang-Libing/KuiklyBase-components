package com.tencent.qqlive.kmm.vbpbservice.export

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved.
 * 类功能描述:请求重试策略监听接口
 *
 * @author woodyjjli
 * @date 2024/9/2
 */
@OptIn(ExperimentalObjCName::class)
interface IVBPBAutoRetry {
    /**
     * 自动重试总开关
     */
    @ObjCName("pb_getEnabled")
    fun getEnabled(): Boolean

    /**
     * 网络回包成功返回给监听者的接口
     *
     * @param pbAutoRetryRequestParams 重试策略接口参数
     */
    @ObjCName("pb_onRequestSuc")
    fun onRequestSuc(pbAutoRetryRequestParams: VBPBAutoRetrySuccessRequestParams)

    /**
     * 网络回包失败返回给监听者的接口
     *
     * @param pbAutoRetryRequestParams 重试策略接口参数
     * @return VBPBAutoRetryStrategy 重试策略
     */
    @ObjCName("pb_onRequestFail")
    fun onRequestFail(pbAutoRetryRequestParams: VBPBAutoRetryFailureRequestParams): VBPBAutoRetryStrategy?
}