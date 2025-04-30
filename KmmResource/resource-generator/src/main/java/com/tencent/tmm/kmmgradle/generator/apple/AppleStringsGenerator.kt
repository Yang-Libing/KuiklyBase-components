/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.apple

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.KeyType
import com.tencent.tmm.kmmgradle.generator.LanguageType
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.StringsGenerator
import com.tencent.tmm.kmmgradle.utils.Constants
import org.apache.commons.text.StringEscapeUtils
import org.gradle.api.file.FileTree
import java.io.File

class AppleStringsGenerator(
    stringsFileTree: FileTree,
    strictLineBreaks: Boolean,
    private val baseLocalizationRegion: String
) : StringsGenerator(stringsFileTree, strictLineBreaks),
    ObjectBodyExtendable by AppleGeneratorHelper() {

    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyInitializer(key: String) =
        CodeBlock.of(
            "StringResource(resourceId = %S, bundle = ${Constants.platformInitializeArgsIOS})",
            key
        )

    override fun generateResources(
        resourcesGenerationDir: File,
        language: LanguageType,
        strings: Map<KeyType, String>
    ) {
        val resDir = File(resourcesGenerationDir, language.appleResourcesDir)
        val localizableFile = File(resDir, "Localizable.strings")
        resDir.mkdirs()

        val content = strings.mapValues { (_, value) ->
            convertXmlStringToAppleLocalization(value)
        }.map { (key, value) ->
            "\"$key\" = \"$value\";"
        }.joinToString("\n")
        localizableFile.writeText(content)

        if (language == LanguageType.Base) {
            val regionDir = File(resourcesGenerationDir, "$baseLocalizationRegion.lproj")
            regionDir.mkdirs()
            val regionFile = File(regionDir, "Localizable.strings")
            regionFile.writeText(content)
        }
    }

    private fun convertXmlStringToAppleLocalization(input: String): String {
        val xmlDecoded = StringEscapeUtils.unescapeXml(input)
        return xmlDecoded.replace("\n", "\\n")
            .replace("\"", "\\\"")
    }
}
