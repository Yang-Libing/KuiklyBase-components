/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource

interface ResourceContainer<T> {
    val platformInfo: ResourcePlatformInfo
}

expect class ResourcePlatformInfo
