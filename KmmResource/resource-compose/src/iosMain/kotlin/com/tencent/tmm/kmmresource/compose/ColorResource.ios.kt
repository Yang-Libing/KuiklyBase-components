package com.tencent.tmm.kmmresource.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.tencent.tmm.kmmresource.resource.ColorResource
import kotlinx.cinterop.DoubleVarOf
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.CoreGraphics.CGFloat
import platform.UIKit.UIColor
import platform.UIKit.colorNamed

fun ColorResource.getUIColor(): UIColor {
    val color: UIColor? = UIColor.colorNamed(
        name = this.name,
        inBundle = this.bundle,
        compatibleWithTraitCollection = null
    )
    return requireNotNull(color) {
        "Can't read color $name from bundle $bundle, please check kmm-resources gradle configuration"
    }
}

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun colorResource(resource: ColorResource): Color {
    return remember(resource) {
        val uiColor: UIColor = resource.getUIColor()

        memScoped {
            val red: DoubleVarOf<CGFloat> = alloc()
            val green: DoubleVarOf<CGFloat> = alloc()
            val blue: DoubleVarOf<CGFloat> = alloc()
            val alpha: DoubleVarOf<CGFloat> = alloc()

            uiColor.getRed(
                red = red.ptr,
                green = green.ptr,
                blue = blue.ptr,
                alpha = alpha.ptr
            )

            Color(
                red = red.value.toFloat(),
                green = green.value.toFloat(),
                blue = blue.value.toFloat(),
                alpha = alpha.value.toFloat()
            )
        }
    }
}