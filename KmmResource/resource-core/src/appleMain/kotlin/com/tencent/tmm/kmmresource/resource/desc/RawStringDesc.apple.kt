package com.tencent.tmm.kmmresource.resource.desc

actual data class RawStringDesc actual constructor(
    val string: String
) : StringDesc {
    override fun localized(): String = string
}