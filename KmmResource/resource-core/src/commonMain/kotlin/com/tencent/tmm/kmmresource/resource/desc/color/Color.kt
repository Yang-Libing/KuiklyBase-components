/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc.color

data class Color(
    val red: Int,
    val green: Int,
    val blue: Int,
    val alpha: Int
) {
    @Suppress("MagicNumber")
    val rgba: Long = alpha.toLong() or
        blue.toLong().shl(8) or
        green.toLong().shl(16) or
        red.toLong().shl(24)

    @Suppress("MagicNumber")
    val argb: Long = blue.toLong() or
        green.toLong().shl(8) or
        red.toLong().shl(16) or
        alpha.toLong().shl(24)

    @Suppress("MagicNumber")
    constructor(colorRGBA: Long) : this(
        red = (colorRGBA.shr(24) and 0xFF).toInt(),
        green = (colorRGBA.shr(16) and 0xFF).toInt(),
        blue = (colorRGBA.shr(8) and 0xFF).toInt(),
        alpha = (colorRGBA.shr(0) and 0xFF).toInt()
    )

    companion object
}