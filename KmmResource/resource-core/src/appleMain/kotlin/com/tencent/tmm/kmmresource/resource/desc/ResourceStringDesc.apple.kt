package com.tencent.tmm.kmmresource.resource.desc

import com.tencent.tmm.kmmresource.internal.Utils
import com.tencent.tmm.kmmresource.resource.StringResource

actual data class ResourceStringDesc actual constructor(
    val stringRes: StringResource
) : StringDesc {
    override fun localized(): String = Utils.localizedString(stringRes)
}