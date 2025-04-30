/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc.color

import com.tencent.tmm.kmmresource.resource.ColorResource

class ColorDescResource(val resource: ColorResource) : ColorDesc

@Suppress("FunctionName")
fun ColorDesc.Companion.Resource(resource: ColorResource): ColorDesc = ColorDescResource(resource)

fun ColorResource.asColorDesc() = ColorDesc.Resource(this)
