package com.tencent.tmm.kmmresource.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.tencent.tmm.kmmresource.resource.FontResource

@Composable
fun fontFamilyResource(fontResource: FontResource): FontFamily {
    return fontResource.asFont()
        ?.let { FontFamily(it) }
        ?: FontFamily.Default
}

@Composable
expect fun FontResource.asFont(
    weight: FontWeight = FontWeight.Normal,
    style: FontStyle = FontStyle.Normal,
): Font?
