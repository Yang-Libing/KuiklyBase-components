/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc


actual data class RawStringDesc actual constructor(val string: String) : StringDesc {
    override fun toString() = string
}
