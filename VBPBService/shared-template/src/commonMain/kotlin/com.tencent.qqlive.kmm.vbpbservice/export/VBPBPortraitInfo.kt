package com.tencent.qqlive.kmm.vbpbservice.export;

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 用户画像信息
 *
 * @author haibarawang
 * @date 2024/4/13 14:41
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBPortraitInfo")
data class VBPBPortraitInfo(
    @ObjCName("mKey")
    var mKey: String? = null,
    @ObjCName("mValueInfoList")
    var mValueInfoList: Array<String>? = null
)