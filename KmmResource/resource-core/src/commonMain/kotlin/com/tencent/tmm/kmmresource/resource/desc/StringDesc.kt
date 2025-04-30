/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc

import com.tencent.tmm.kmmresource.resource.PluralsResource
import com.tencent.tmm.kmmresource.resource.StringResource

fun String.desc() = StringDesc.Raw(this)
fun StringResource.desc() = StringDesc.Resource(this)
fun PluralsResource.desc(number: Int) = StringDesc.Plural(this, number)

operator fun StringDesc.plus(other: StringDesc): StringDesc =
    StringDesc.Composition(listOf(this, other))

fun Iterable<StringDesc>.joinToStringDesc(separator: String = ", "): StringDesc =
    StringDesc.Composition(this, separator)

expect interface StringDesc {

    sealed class LocaleType {
        object System : LocaleType
        class Custom(@Suppress("UnusedPrivateMember") locale: String) : LocaleType
    }

    companion object {
        var localeType: LocaleType
    }
}
