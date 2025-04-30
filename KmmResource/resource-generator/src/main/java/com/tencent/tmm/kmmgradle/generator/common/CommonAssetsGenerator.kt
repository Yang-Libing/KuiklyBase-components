/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.common

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.AssetsGenerator
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import org.gradle.api.file.SourceDirectorySet

class CommonAssetsGenerator(
    sourceDirectorySet: SourceDirectorySet
) : AssetsGenerator(sourceDirectorySet), ObjectBodyExtendable by CommonObjectBodyExtendable() {

    override fun getClassModifiers(): Array<KModifier> = emptyArray()

    override fun getPropertyModifiers(): Array<KModifier> = emptyArray()

    override fun getPropertyInitializer(fileSpec: AssetSpecFile): CodeBlock? = null
}
