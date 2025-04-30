/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.common

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.tencent.tmm.kmmgradle.generator.ColorNode
import com.tencent.tmm.kmmgradle.generator.ColorsGenerator
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import org.gradle.api.file.FileTree

class CommonColorsGenerator(
    colorsFileTree: FileTree
) : ColorsGenerator(colorsFileTree), ObjectBodyExtendable by CommonObjectBodyExtendable() {

    override fun getImports(): List<ClassName> = emptyList()

    override fun getPropertyInitializer(color: ColorNode): CodeBlock? = null
}
