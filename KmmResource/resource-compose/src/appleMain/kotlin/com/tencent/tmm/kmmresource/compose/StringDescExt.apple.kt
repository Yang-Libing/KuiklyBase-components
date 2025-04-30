package com.tencent.tmm.kmmresource.compose

import androidx.compose.runtime.Composable
import com.tencent.tmm.kmmresource.resource.desc.StringDesc

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
@Composable
actual fun StringDesc.localized(): String =
    // no recursion here - we call localized from class StringDesc
    this.localized()