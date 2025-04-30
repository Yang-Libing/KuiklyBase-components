package com.tencent.tmm.knoi

import com.tencent.tmm.kmmresource.compose.getUIColor
import com.tencent.tmm.kmmresource.resource.desc.Resource
import com.tencent.tmm.kmmresource.resource.desc.StringDesc
import com.tencent.tmm.kmmresource.sample.MR
import platform.UIKit.UIImage

fun getBundleString(): String {
    return StringDesc.Resource(MR.strings.hello_world).localized()
}

fun getColor(): platform.CoreImage.CIColor {
    MR.colors.platformInfo.nsBundle
    return MR.colors.textColor.getUIColor().CIColor
}
