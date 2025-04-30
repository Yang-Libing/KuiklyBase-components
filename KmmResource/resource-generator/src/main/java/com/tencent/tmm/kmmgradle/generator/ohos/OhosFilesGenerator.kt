/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.ohos

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.FilesGenerator
import com.tencent.tmm.kmmgradle.generator.MRGenerator
import com.tencent.tmm.kmmgradle.generator.NOPObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import org.gradle.api.file.FileTree
import java.io.File

class OhosFilesGenerator(
    inputFileTree: FileTree,
    private val mrSettings: MRGenerator.MRSettings
) : FilesGenerator(inputFileTree), ObjectBodyExtendable by NOPObjectBodyExtendable() {
    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyInitializer(fileSpec: FileSpec) =
        CodeBlock.of("FileResource(rawResName = %S)", "files/${getFileName(fileSpec)}")

    override fun generateResources(resourcesGenerationDir: File, files: List<FileSpec>) {
        if (files.isNullOrEmpty()) {
            return
        }

        val filesDir = File(resourcesGenerationDir, HARMONY_FILE_DIR).apply {
            mkdirs()
        }
        files.forEach { fileSpec ->
            fileSpec.file.copyTo(File(filesDir, getFileName(fileSpec)))
        }
    }

    private fun getFileName(fileSpec: FileSpec): String =
        "${mrSettings.resPrefix}${fileSpec.file.name}"

    companion object {
        private const val HARMONY_FILE_DIR = "rawfile/files"
    }
}
