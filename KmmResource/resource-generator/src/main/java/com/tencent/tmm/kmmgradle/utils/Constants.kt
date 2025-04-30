package com.tencent.tmm.kmmgradle.utils

import com.squareup.kotlinpoet.ClassName

object Constants {
    val resourcePlatformInfoName = ClassName("com.tencent.tmm.kmmresource.resource", "ResourcePlatformInfo")
    val platformInfoName = "platformInfo"
    val platformInitialize = "ResourcePlatformInfo()"
    val platformInitializeIOS = "ResourcePlatformInfo(bundle)"
    val platformInitializeArgsIOS = "platformInfo.nsBundle"



    val stringResourceName = ClassName("com.tencent.tmm.kmmresource.resource", "StringResource")
    val pluralsResourceName = ClassName("com.tencent.tmm.kmmresource.resource", "PluralsResource")
    val imageResourceName = ClassName("com.tencent.tmm.kmmresource.resource", "ImageResource")
    val colorResourceName = ClassName("com.tencent.tmm.kmmresource.resource", "ColorResource")
    val fontResourceName = ClassName("com.tencent.tmm.kmmresource.resource", "FontResource")
    val fileResourceName = ClassName("com.tencent.tmm.kmmresource.resource", "FileResource")
    val assetResourceName = ClassName("com.tencent.tmm.kmmresource.resource", "AssetResource")
}