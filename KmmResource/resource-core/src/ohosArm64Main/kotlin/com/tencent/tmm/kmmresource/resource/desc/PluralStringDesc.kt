/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc

import com.tencent.tmm.kmmresource.resource.PluralsResource
import com.tencent.tmm.kmmresource.utils.OhosKmmResourceManager

actual data class PluralStringDesc actual constructor(
    val pluralsRes: PluralsResource,
    val number: Int
) : StringDesc {
    override fun toString(): String {
        return OhosKmmResourceManager.getPlural(pluralsRes.resourceName, number)
            ?: throw Exception("pluralsRes getError")
    }
}
