/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.android

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.FilesGenerator
import com.tencent.tmm.kmmgradle.generator.MRGenerator
import com.tencent.tmm.kmmgradle.generator.NOPObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import org.gradle.api.file.FileTree
import java.io.File
import java.util.Locale

class AndroidFilesGenerator(
    inputFileTree: FileTree,
    private val getAndroidRClassPackage: () -> String,
    private val mrSettings: MRGenerator.MRSettings
) : FilesGenerator(inputFileTree), ObjectBodyExtendable by NOPObjectBodyExtendable() {
    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyInitializer(fileSpec: FileSpec) =
        CodeBlock.of("FileResource(rawResId = R.raw.%L)", keyToResourceId(fileSpec.key))

    override fun getImports() = listOf(
        ClassName(getAndroidRClassPackage(), "R")
    )

    override fun generateResources(
        resourcesGenerationDir: File,
        files: List<FileSpec>
    ) {
        val targetDir = File(resourcesGenerationDir, "raw")
        targetDir.mkdirs()

        files.forEach { fileSpec ->
            val fileName = keyToResourceId(fileSpec.key) + "." + fileSpec.file.extension
            fileSpec.file.copyTo(File(targetDir, fileName))
        }
    }

    private fun keyToResourceId(key: String): String {
        val processKey = "${mrSettings.resPrefix}${key}"
        return processKey.lowercase(Locale.ROOT)
    }
}
