/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc.color

import com.tencent.tmm.kmmresource.utils.OhosKmmResourceManager

fun ColorDesc.getColor(): Int {
    return when (this) {
        is ColorDescResource -> OhosKmmResourceManager.getColor(resource.resourceName)
            ?: throw Exception("can not find ${resource.resourceName} color")

        is ColorDescSingle -> color.argb.toInt()
        is ColorDescThemed -> this.lightColor.argb.toInt()

        else -> throw IllegalArgumentException("unknown class ${this::class}")
    }
}
