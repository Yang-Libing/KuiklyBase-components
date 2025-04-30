/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource

import com.tencent.tmm.kmmresource.resource.desc.Plural
import com.tencent.tmm.kmmresource.resource.desc.StringDesc


expect class PluralsResource

fun PluralsResource.format(number: Int) = StringDesc.Plural(this, number)