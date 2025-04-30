/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.common

import com.squareup.kotlinpoet.CodeBlock
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.StringsGenerator
import org.gradle.api.file.FileTree

class CommonStringsGenerator(
    stringsFileTree: FileTree,
    strictLineBreaks: Boolean
) : StringsGenerator(stringsFileTree, strictLineBreaks),
    ObjectBodyExtendable by CommonObjectBodyExtendable() {
    override fun getPropertyInitializer(key: String): CodeBlock? = null
}
