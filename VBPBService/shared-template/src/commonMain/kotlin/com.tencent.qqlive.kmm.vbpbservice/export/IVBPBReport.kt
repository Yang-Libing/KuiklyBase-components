package com.tencent.qqlive.kmm.vbpbservice.export

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 上报接口
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
@OptIn(ExperimentalObjCName::class)
interface IVBPBReport {
    /**
     * 上报采样率
     * 返回值为分母，例如采样率为 1/1000，那么返回值应该是 1000
     */
    @ObjCName("pb_reportSampleRate")
    fun reportSampleRate(cmd: String, isSuccess: Boolean): Int

    /**
     * 上报统计信息
     */
    @ObjCName("pb_report")
    fun report(reportInfo: Map<String,String>)
}