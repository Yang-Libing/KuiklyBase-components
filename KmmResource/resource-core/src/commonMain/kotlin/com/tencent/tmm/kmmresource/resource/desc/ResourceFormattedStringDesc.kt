/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc

import com.tencent.tmm.kmmresource.resource.StringResource

@Suppress("FunctionName")
fun StringDesc.Companion.ResourceFormatted(
    stringRes: StringResource,
    args: List<Any>
) = ResourceFormattedStringDesc(stringRes, args)

expect class ResourceFormattedStringDesc(stringRes: StringResource, args: List<Any>) : StringDesc
