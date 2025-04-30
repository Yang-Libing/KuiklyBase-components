/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc

import com.tencent.tmm.kmmresource.resource.StringResource
import com.tencent.tmm.kmmresource.utils.OhosKmmResourceManager
actual data class ResourceFormattedStringDesc actual constructor(
    val stringRes: StringResource,
    val args: List<Any>
) : StringDesc {
    override fun toString(): String {
        @Suppress("SpreadOperator")
        return OhosKmmResourceManager.getString(stringRes.resourceName, *args.toTypedArray())
            ?: throw Exception()
    }
}
