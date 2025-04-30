/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc

import com.tencent.tmm.kmmresource.resource.StringResource

@Suppress("FunctionName")
fun StringDesc.Companion.Resource(stringRes: StringResource) = ResourceStringDesc(stringRes)

expect class ResourceStringDesc(stringRes: StringResource) : StringDesc
