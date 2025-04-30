/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.android

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.ColorNode
import com.tencent.tmm.kmmgradle.generator.ColorsGenerator
import com.tencent.tmm.kmmgradle.generator.MRGenerator
import com.tencent.tmm.kmmgradle.generator.NOPObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import org.gradle.api.file.FileTree
import java.io.File

class AndroidColorsGenerator(
    colorsFileTree: FileTree,
    private val mrSettings: MRGenerator.MRSettings
) : ColorsGenerator(colorsFileTree), ObjectBodyExtendable by NOPObjectBodyExtendable() {

    override fun getImports() = emptyList<ClassName>()

    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun generateResources(resourcesGenerationDir: File, colors: List<ColorNode>) {
        val valuesDir = File(resourcesGenerationDir, "values")
        val defaultStringsFile = File(valuesDir, COLORS_XML_FILE_NAME)
        valuesDir.mkdirs()

        val valuesNightDir = File(resourcesGenerationDir, "values-night")
        val darkStringsFile = File(valuesNightDir, COLORS_XML_FILE_NAME)
        valuesNightDir.mkdirs()

        val header =
            """
            <?xml version="1.0" encoding="utf-8"?>
            <resources>
            """.trimIndent()

        val footer =
            """
            </resources>
            """.trimIndent()

        val defaultContent = colors.joinToString("\n") { colorNode ->
            if (colorNode.isThemed()) {
                buildColorString(colorNode.name, replaceColorAlpha(colorNode.lightColor))
            } else {
                buildColorString(colorNode.name, replaceColorAlpha(colorNode.singleColor))
            }
        }

        val darkContent = colors.filter { it.isThemed() }.joinToString("\n") { colorNode ->
            buildColorString(colorNode.name, replaceColorAlpha(colorNode.darkColor))
        }

        defaultStringsFile.writeText(header + "\n")
        defaultStringsFile.appendText(defaultContent)
        defaultStringsFile.appendText("\n" + footer)

        darkStringsFile.writeText(header + "\n")
        darkStringsFile.appendText(darkContent)
        darkStringsFile.appendText("\n" + footer)
    }

    private fun buildColorString(name: String, colorCode: String?): String =
        "\t<color name=\"${getColorResName(name)}\">#$colorCode</color>"

    override fun getPropertyInitializer(color: ColorNode): CodeBlock =
        CodeBlock.of("ColorResource(resourceId = R.color.${getColorResName(color.name)})")

    private fun getColorResName(name: String) = "${mrSettings.resPrefix}${name}"

    companion object {
        private const val COLORS_XML_FILE_NAME = "colors.xml"
    }
}
