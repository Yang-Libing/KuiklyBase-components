/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource.desc.image

class ImageDescUrl(val url: String) : ImageDesc

@Suppress("FunctionName")
fun ImageDesc.Companion.Url(url: String): ImageDesc = ImageDescUrl(url)

fun String.asImageUrl(): ImageDesc = ImageDesc.Url(this)
