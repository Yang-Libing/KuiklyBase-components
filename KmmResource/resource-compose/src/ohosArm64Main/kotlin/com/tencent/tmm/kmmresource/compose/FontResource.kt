package com.tencent.tmm.kmmresource.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import com.tencent.tmm.kmmresource.resource.FontResource
import com.tencent.tmm.kmmresource.utils.OhosKmmResourceManager

@Composable
actual fun FontResource.asFont(
    weight: FontWeight,
    style: FontStyle,
): Font? = remember(fontResourcePath) {
    Font(
        identity = fontResourcePath,
        data = OhosKmmResourceManager.getFont(fontResourcePath)!!,
        weight = weight,
        style = style,
    )
}
