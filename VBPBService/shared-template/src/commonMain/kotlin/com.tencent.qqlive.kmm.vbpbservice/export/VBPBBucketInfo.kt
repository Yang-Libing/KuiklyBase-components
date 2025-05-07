package com.tencent.qqlive.kmm.vbpbservice.export;

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved
 *
 * PB 分桶信息
 *
 * @author haibarawang
 * @date 2024/3/1 19:41
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBBucketInfo")
data class VBPBBucketInfo(
    // 分桶Id
    @ObjCName("mBucketId")
    var mBucketId: Int = 0,

    // 额外信息
    @ObjCName("mExtra")
    var mExtra: String? = null
)
