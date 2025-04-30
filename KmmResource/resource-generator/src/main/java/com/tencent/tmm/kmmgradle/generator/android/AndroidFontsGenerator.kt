/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.android

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.FontsGenerator
import com.tencent.tmm.kmmgradle.generator.MRGenerator
import com.tencent.tmm.kmmgradle.generator.NOPObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import org.gradle.api.file.FileTree
import java.io.File
import java.util.Locale

class AndroidFontsGenerator(
    inputFileTree: FileTree,
    private val getAndroidRClassPackage: () -> String,
    private val mrSettings: MRGenerator.MRSettings
) : FontsGenerator(inputFileTree), ObjectBodyExtendable by NOPObjectBodyExtendable() {
    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyInitializer(fontFile: File): CodeBlock = CodeBlock.of(
        "FontResource(fontResourceId = R.font.%L)", keyToResourceId(fontFile.nameWithoutExtension)
    )

    override fun getImports(): List<ClassName> = listOf(
        ClassName(getAndroidRClassPackage(), "R")
    )

    override fun generateResources(
        resourcesGenerationDir: File,
        files: List<FontFile>
    ) {
        val fontResDir = File(resourcesGenerationDir, "font")
        fontResDir.mkdirs()

        files.forEach { (key, file) ->
            val fileName = keyToResourceId(key) + "." + file.extension
            file.copyTo(File(fontResDir, fileName))
        }
    }

    private fun keyToResourceId(key: String): String {
        val processKey = "${mrSettings.resPrefix}${key}"
        return processKey.replace("-", "_").lowercase(Locale.ROOT)
    }
}
