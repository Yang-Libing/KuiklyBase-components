package com.tencent.tmm.kmmresource.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.tencent.tmm.kmmresource.resource.FontResource

@Composable
actual fun FontResource.asFont(
    weight: FontWeight,
    style: FontStyle,
): Font? = remember(fontResourceId) {
    Font(
        resId = fontResourceId,
        weight = weight,
        style = style,
    )
}