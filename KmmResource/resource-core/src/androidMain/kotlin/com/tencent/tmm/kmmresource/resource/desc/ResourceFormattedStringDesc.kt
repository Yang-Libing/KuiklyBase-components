/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc

import android.content.Context
import com.tencent.tmm.kmmresource.resource.StringResource

actual data class ResourceFormattedStringDesc actual constructor(
    val stringRes: StringResource,
    val args: List<Any>
) : StringDesc {
    override fun toString(context: Context): String {
        @Suppress("SpreadOperator")
        return Utils.resourcesForContext(context).getString(
            stringRes.resourceId,
            *Utils.processArgs(args, context)
        )
    }
}
