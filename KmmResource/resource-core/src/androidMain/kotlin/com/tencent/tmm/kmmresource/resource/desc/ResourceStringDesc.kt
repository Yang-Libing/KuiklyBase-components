/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc

import android.content.Context
import com.tencent.tmm.kmmresource.resource.StringResource

actual data class ResourceStringDesc actual constructor(
    val stringRes: StringResource
) : StringDesc {
    override fun toString(context: Context): String =
        Utils.resourcesForContext(context).getString(stringRes.resourceId)
}
