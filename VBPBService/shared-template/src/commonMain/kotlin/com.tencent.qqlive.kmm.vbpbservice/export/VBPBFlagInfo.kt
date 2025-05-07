package com.tencent.qqlive.kmm.vbpbservice.export;

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 审核状态信息
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBFlagInfo")
data class VBPBFlagInfo(
    // 是否在审核状态 true：审核状态 false：非审核状态
    @ObjCName("mIsChecking")
    var mIsChecking: Boolean = false,
    // 调试标志  true：调试请求 false: 普通请求
    @ObjCName("mIsDebugEnabled")
    var mIsDebugEnabled: Boolean = false
)