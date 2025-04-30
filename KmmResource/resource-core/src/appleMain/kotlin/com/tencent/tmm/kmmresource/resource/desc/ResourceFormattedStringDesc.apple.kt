package com.tencent.tmm.kmmresource.resource.desc

import com.tencent.tmm.kmmresource.internal.Utils
import com.tencent.tmm.kmmresource.resource.StringResource

actual data class ResourceFormattedStringDesc actual constructor(
    val stringRes: StringResource,
    val args: List<Any>
) : StringDesc {
    override fun localized(): String =
        Utils.stringWithFormat(Utils.localizedString(stringRes), Utils.processArgs(args))
}