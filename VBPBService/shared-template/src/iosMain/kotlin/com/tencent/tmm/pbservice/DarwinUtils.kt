package com.tencent.tmm.pbservice

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.convert
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.NSMutableData
import platform.Foundation.appendBytes
import platform.posix.memcpy
import kotlin.experimental.ExperimentalObjCName

/**
 * Copyright (c) 2022 Tencent. All rights reserved.
 *  IOS端工具类
 * @author juniuschen
 * @date 2022/8/29
 */


/**
 * ByteArray转NSData
 */
@OptIn(ExperimentalForeignApi::class, ExperimentalObjCName::class)
@ObjCName("toNSData")
fun ByteArray.toNSData(): NSData = NSMutableData().apply {
    if (isEmpty()) return@apply
    this@toNSData.usePinned {
        appendBytes(it.addressOf(0), size.convert())
    }
}

/**
 * NSData转ByteArray
 */
@OptIn(ExperimentalForeignApi::class, ExperimentalObjCName::class)
@ObjCName("toByteArray")
fun NSData.toByteArray(): ByteArray {
    val result = ByteArray(length.toInt())
    if (result.isEmpty()) return result

    result.usePinned {
        memcpy(it.addressOf(0), bytes, length)
    }

    return result
}
