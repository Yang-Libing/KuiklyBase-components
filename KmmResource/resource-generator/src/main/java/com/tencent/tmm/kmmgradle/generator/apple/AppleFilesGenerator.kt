/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.apple

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.FilesGenerator
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import com.tencent.tmm.kmmgradle.utils.Constants
import org.gradle.api.file.FileTree
import java.io.File

class AppleFilesGenerator(
    inputFileTree: FileTree
) : FilesGenerator(inputFileTree = inputFileTree), ObjectBodyExtendable by AppleGeneratorHelper() {

    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyInitializer(fileSpec: FileSpec) = CodeBlock.of(
        "FileResource(fileName = %S, extension = %S,bundle = ${Constants.platformInitializeArgsIOS})",
        fileSpec.file.nameWithoutExtension,
        fileSpec.file.extension
    )

    override fun generateResources(
        resourcesGenerationDir: File,
        files: List<FileSpec>
    ) {
        val targetDir = File(resourcesGenerationDir, "files")
        targetDir.mkdirs()

        files.forEach { fileSpec ->
            fileSpec.file.copyTo(File(targetDir, fileSpec.file.name))
        }
    }
}
