 /*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc

import com.tencent.tmm.kmmresource.resource.PluralsResource
import com.tencent.tmm.kmmresource.resource.StringResource

fun String.strResDesc() = StringResource(this).desc()
fun String.plrResDesc(number: Int) = PluralsResource(this).desc(number)
