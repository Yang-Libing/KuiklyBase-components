/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc.image

import com.tencent.tmm.kmmresource.resource.ImageResource

class ImageDescResource(val resource: ImageResource) : ImageDesc

@Suppress("FunctionName")
fun ImageDesc.Companion.Resource(resource: ImageResource): ImageDesc = ImageDescResource(resource)

fun ImageResource.asImageDesc() = ImageDesc.Resource(this)
