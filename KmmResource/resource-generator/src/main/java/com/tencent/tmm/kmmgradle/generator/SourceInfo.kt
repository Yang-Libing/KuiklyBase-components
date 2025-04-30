/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator

import org.gradle.api.file.SourceDirectorySet
import java.io.File

data class SourceInfo(
    val generatedDir: File,
    val commonResources: SourceDirectorySet,
    val mrClassPackage: String,
    var androidRClassPackage: String? = null
)
