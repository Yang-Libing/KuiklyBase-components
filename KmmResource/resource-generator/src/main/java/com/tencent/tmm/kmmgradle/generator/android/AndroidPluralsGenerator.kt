/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.android

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.KeyType
import com.tencent.tmm.kmmgradle.generator.LanguageType
import com.tencent.tmm.kmmgradle.generator.MRGenerator
import com.tencent.tmm.kmmgradle.generator.NOPObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.PluralMap
import com.tencent.tmm.kmmgradle.generator.PluralsGenerator
import org.apache.commons.lang3.StringEscapeUtils
import org.gradle.api.file.FileTree
import java.io.File

class AndroidPluralsGenerator(
    pluralsFileTree: FileTree,
    strictLineBreaks: Boolean,
    private val getAndroidRClassPackage: () -> String,
    private val mrSettings: MRGenerator.MRSettings
) : PluralsGenerator(pluralsFileTree, strictLineBreaks),
    ObjectBodyExtendable by NOPObjectBodyExtendable() {
    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyInitializer(key: String) =
        CodeBlock.of("PluralsResource(R.plurals.%L)", processKey(key))

    override fun getImports(): List<ClassName> = listOf(
        ClassName(getAndroidRClassPackage(), "R")
    )

    override fun generateResources(
        resourcesGenerationDir: File,
        language: LanguageType,
        strings: Map<KeyType, PluralMap>
    ) {
        val valuesDir = File(resourcesGenerationDir, language.androidResourcesDir)
        val stringsFile = File(valuesDir, "multiplatform_plurals.xml")
        valuesDir.mkdirs()

        val header = """
            <?xml version="1.0" encoding="utf-8"?>
            <resources>
            """.trimIndent()

        val content = strings.map { (key, pluralMap) ->
            val processedKey = processKey(key)
            val start = "\t<plurals name=\"$processedKey\">\n"
            val items = pluralMap.map { (quantity, value) ->
                val processedValue = StringEscapeUtils.escapeXml(value)
                "\t\t<item quantity=\"$quantity\">$processedValue</item>"
            }.joinToString("\n")
            val end = "\n\t</plurals>"

            start + items + end
        }.joinToString("\n")

        val footer = """
            </resources>
            """.trimIndent()

        stringsFile.writeText(header + "\n")
        stringsFile.appendText(content)
        stringsFile.appendText("\n" + footer)
    }

    private fun processKey(key: String): String {
        val processKey = "${mrSettings.resPrefix}${key}"
        return processKey.replace(".", "_")
    }
}
