/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc.color

@Suppress("MagicNumber")
fun Color.Companion.parseColor(colorHEX: String): Color {
    if (colorHEX[0] != '#') throw IllegalArgumentException("Unknown color")
    var colorARGB = colorHEX.substring(1).toLong(16)
    if (colorHEX.length == 7) {
        colorARGB = colorARGB or 0x00000000ff000000
    } else if (colorHEX.length != 9) {
        throw IllegalArgumentException("Unknown color")
    }
    return Color(
        alpha = (colorARGB.shr(24) and 0xFF).toInt(),
        red = (colorARGB.shr(16) and 0xFF).toInt(),
        green = (colorARGB.shr(8) and 0xFF).toInt(),
        blue = (colorARGB.shr(0) and 0xFF).toInt(),
    )
}