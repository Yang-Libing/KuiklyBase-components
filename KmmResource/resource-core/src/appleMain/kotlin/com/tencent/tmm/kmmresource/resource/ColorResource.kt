/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource

import platform.Foundation.NSBundle

actual class ColorResource(
    val name: String,
    val bundle: NSBundle = NSBundle.mainBundle
)
