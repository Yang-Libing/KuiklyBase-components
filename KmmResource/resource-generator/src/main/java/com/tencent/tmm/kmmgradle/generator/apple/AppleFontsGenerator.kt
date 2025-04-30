/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.apple

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.FontsGenerator
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import com.tencent.tmm.kmmgradle.utils.Constants
import org.gradle.api.file.FileTree
import java.io.File

class AppleFontsGenerator(
    inputFileTree: FileTree
) : FontsGenerator(inputFileTree), ObjectBodyExtendable by AppleGeneratorHelper() {

    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyInitializer(fontFile: File): CodeBlock {
        return CodeBlock.of(
            "FontResource(fontName = %S, bundle = ${Constants.platformInitializeArgsIOS})",
            fontFile.name
        )
    }

    override fun generateResources(
        resourcesGenerationDir: File,
        files: List<FontFile>
    ) {
        files.forEach { (_, file) ->
            file.copyTo(File(resourcesGenerationDir, file.name))
        }
    }
}
