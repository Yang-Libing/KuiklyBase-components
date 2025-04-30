/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc.color

import androidx.annotation.ColorInt

@ColorInt
fun Color.colorInt(): Int = argb.toInt()