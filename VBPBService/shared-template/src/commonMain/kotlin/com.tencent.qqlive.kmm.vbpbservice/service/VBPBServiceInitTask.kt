package com.tencent.qqlive.kmm.vbpbservice.service

import com.tencent.qqlive.kmm.vbpbservice.export.VBPBInitConfig
import kotlin.concurrent.Volatile
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB服务初始化任务
 *
 * @author haibarawang
 * @date 2024/3/4 19:41
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBServiceInitTask")
object VBPBServiceInitTask {

    @Volatile
    private var isInit = false

    @ObjCName("isInit")
    fun isInit(): Boolean = isInit

    @ObjCName("init")
    fun init(initConfig: VBPBInitConfig) {
        VBPBServiceInitHelper.init(initConfig)
        isInit = true
    }

}