/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc.color

class ColorDescSingle(val color: Color) : ColorDesc

@Suppress("FunctionName")
fun ColorDesc.Companion.Single(color: Color): ColorDesc = ColorDescSingle(color)

fun Color.asColorDesc() = ColorDesc.Single(this)
