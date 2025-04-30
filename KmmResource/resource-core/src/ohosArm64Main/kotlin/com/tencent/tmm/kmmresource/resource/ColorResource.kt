/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource

import com.tencent.tmm.kmmresource.utils.OhosKmmResourceManager

actual class ColorResource(
    val resourceName: String
) {
    fun getColor(): Int = OhosKmmResourceManager.getColor(resourceName)
        ?: throw Exception("can not find ${resourceName} target color")
}
