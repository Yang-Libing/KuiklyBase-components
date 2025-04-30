/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.common

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.FilesGenerator
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import org.gradle.api.file.FileTree

class CommonFilesGenerator(
    inputFileTree: FileTree
) : FilesGenerator(inputFileTree), ObjectBodyExtendable by CommonObjectBodyExtendable() {
    override fun getClassModifiers(): Array<KModifier> = emptyArray()

    override fun getPropertyModifiers(): Array<KModifier> = emptyArray()

    override fun getPropertyInitializer(fileSpec: FileSpec): CodeBlock? = null
}
