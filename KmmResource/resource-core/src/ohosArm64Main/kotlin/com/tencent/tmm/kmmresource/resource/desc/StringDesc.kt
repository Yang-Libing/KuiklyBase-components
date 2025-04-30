/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc


actual interface StringDesc {
    actual sealed class LocaleType {
        actual object System : LocaleType()
        actual class Custom actual constructor(@Suppress("UnusedPrivateMember") locale: String) :
            LocaleType()
    }

    actual companion object {
        actual var localeType: LocaleType
            get() = LocaleType.System
            set(value) {}
    }

}
