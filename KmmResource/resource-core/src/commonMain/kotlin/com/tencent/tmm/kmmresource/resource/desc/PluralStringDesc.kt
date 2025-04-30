/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc

import com.tencent.tmm.kmmresource.resource.PluralsResource

@Suppress("FunctionName")
fun StringDesc.Companion.Plural(
    pluralsRes: PluralsResource,
    number: Int
) = PluralStringDesc(pluralsRes, number)

expect class PluralStringDesc(pluralsRes: PluralsResource, number: Int) : StringDesc
