package com.tencent.qqlive.kmm.vbpbservice.export

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 初始化配置
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBInitConfig")
class VBPBInitConfig(
    @ObjCName("reportImpl")
    var reportImpl: IVBPBReport? = null,
    @ObjCName("logImpl")
    var logImpl: IVBPBLog? = null,
    @ObjCName("deviceInfo")
    var deviceInfo: IVBPBDeviceInfo? = null,
    @ObjCName("versionInfo")
    var versionInfo: IVBPBVersionInfo? = null,
    @ObjCName("config")
    var config: IVBPBConfig? = null,
    @ObjCName("personalizeInfo")
    var personalizeInfo: IVBPBPersonalize? = null,
    @ObjCName("autoRetry")
    var autoRetry: IVBPBAutoRetry? = null,
    @ObjCName("quicConfig")
    var quicConfig: IVBPBQUICConfig? = null,
    @ObjCName("requestIdGenerator")
    var requestIdGenerator: IVBPBRequestIdGenerator? = null,
    @ObjCName("tabProxy")
    var tabProxy: IVBPBTab? = null
)