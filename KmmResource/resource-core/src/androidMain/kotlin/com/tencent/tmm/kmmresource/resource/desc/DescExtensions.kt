/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc

import com.tencent.tmm.kmmresource.resource.PluralsResource
import com.tencent.tmm.kmmresource.resource.StringResource

fun Int.strResDesc() = StringResource(this).desc()
fun Int.plrResDesc(number: Int) = PluralsResource(this).desc(number)
