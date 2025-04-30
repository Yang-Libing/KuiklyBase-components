/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc.color

class ColorDescThemed(val lightColor: Color, val darkColor: Color) : ColorDesc

@Suppress("FunctionName")
fun ColorDesc.Companion.Themed(
    lightColor: Color,
    darkColor: Color
): ColorDesc = ColorDescThemed(
    lightColor = lightColor,
    darkColor = darkColor
)
