package com.tencent.qqlive.kmm.vbpbservice.export

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB Log接口
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("IVBPBLog")
interface IVBPBLog {

    /**
     * Debug 级别日志输出
     *
     * @param tag     tag
     * @param content 日志内容
     */
    @ObjCName("pb_d")
    fun d(tag: String?, content: String?)

    /**
     * Info 级别日志输出
     *
     * @param tag     tag
     * @param content 日志内容
     */
    @ObjCName("pb_i")
    fun i(tag: String?, content: String?)

    /**
     * Error 级别日志输出
     *
     * @param tag     tag
     * @param content 日志内容
     */
    @ObjCName("pb_e")
    fun e(tag: String?, content: String?, throwable: Throwable? = null)

}