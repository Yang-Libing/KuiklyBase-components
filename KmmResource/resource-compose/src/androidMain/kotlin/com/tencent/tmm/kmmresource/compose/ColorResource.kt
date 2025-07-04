/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.compose

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.tencent.tmm.kmmresource.resource.ColorResource

@Composable
actual fun colorResource(resource: ColorResource): Color {
    val context: Context = LocalContext.current
    return Color(resource.getColor(context))
}
