package com.tencent.tmm.kmmresource.resource

import platform.Foundation.NSBundle

actual data class ImageResource(
    val assetImageName: String,
    val bundle: NSBundle = NSBundle.mainBundle,
    val suffix: String
) {
    companion object {
        const val M_DPI_PATH = "images/mdpi"
        const val X_DPI_PATH = "images/xldpi"
        const val XX_DPI_PATH = "images/xxldpi"
        const val BASE_DPI_PATH = "images/base"
    }
}