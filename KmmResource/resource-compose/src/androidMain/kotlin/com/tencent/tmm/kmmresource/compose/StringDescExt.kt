/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.compose

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.tencent.tmm.kmmresource.resource.desc.StringDesc

@Composable
actual fun StringDesc.localized(): String {
    val context: Context = LocalContext.current
    return toString(context)
}
