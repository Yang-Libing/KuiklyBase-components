/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource

import platform.Foundation.NSBundle

actual data class StringResource(
    val resourceId: String,
    val bundle: NSBundle = NSBundle.mainBundle
)
