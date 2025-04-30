package com.tencent.tmm.kmmresource.resource.desc

actual data class CompositionStringDesc actual constructor(
    val args: Iterable<StringDesc>,
    val separator: String?
) : StringDesc {
    override fun localized(): String =
        args.joinToString(separator = separator ?: "") { it.localized() }
}