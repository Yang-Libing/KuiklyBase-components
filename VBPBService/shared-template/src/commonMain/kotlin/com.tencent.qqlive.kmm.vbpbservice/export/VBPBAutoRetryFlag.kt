package com.tencent.qqlive.kmm.vbpbservice.export

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * Copyright (c) 2024 Tencent. All rights reserved.
 * 类功能描述: 自动重试标志类
 *
 * @author woodyjjli
 * @date 2024/9/5
 */

@OptIn(ExperimentalObjCName::class)
@ObjCName("VBPBAutoRetryFlag")
enum class VBPBAutoRetryFlag(value: Int) {

    NO_RETRY(0),
    NETWORK_ERROR_RETRY(1),
    BUSINESS_ERROR_RETRY(2);

    @ObjCName("pb_autoRetryFlagValue")
    val value: Int

    init {
        this.value = value
    }

}