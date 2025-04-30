/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmresource.resource


actual class AssetResource(val path: String) {
    actual val originalPath: String
        get() = path
}
