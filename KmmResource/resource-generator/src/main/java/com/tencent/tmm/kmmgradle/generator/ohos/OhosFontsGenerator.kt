/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.ohos

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.FontsGenerator
import com.tencent.tmm.kmmgradle.generator.MRGenerator
import com.tencent.tmm.kmmgradle.generator.NOPObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import org.gradle.api.file.FileTree
import java.io.File

class OhosFontsGenerator(
    inputFileTree: FileTree,
    private val mrSettings: MRGenerator.MRSettings
) : FontsGenerator(inputFileTree), ObjectBodyExtendable by NOPObjectBodyExtendable() {
    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyInitializer(fontFile: File): CodeBlock =
        CodeBlock.of("FontResource(fontResourcePath = %S)", "fonts/${getFontName(fontFile)}")

    override fun generateResources(resourcesGenerationDir: File, files: List<FontFile>) {
        if (files.isNullOrEmpty()) {
            return
        }

        val fontsDir = File(resourcesGenerationDir, HARMONY_FONT_DIR).apply {
            mkdirs()
        }

        files.forEach { (key, file) ->
            file.copyTo(File(fontsDir, getFontName(file)))
        }
    }

    private fun getFontName(fontFile: File): String =
        "${mrSettings.resPrefix}${fontFile.name}"

    companion object {
        private const val HARMONY_FONT_DIR = "rawfile/fonts"
    }
}
