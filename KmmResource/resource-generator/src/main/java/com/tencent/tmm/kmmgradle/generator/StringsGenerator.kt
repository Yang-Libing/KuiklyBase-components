/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator

import com.squareup.kotlinpoet.ClassName
import com.tencent.tmm.kmmgradle.generator.android.AndroidStringsGenerator
import com.tencent.tmm.kmmgradle.generator.apple.AppleStringsGenerator
import com.tencent.tmm.kmmgradle.generator.common.CommonStringsGenerator
import com.tencent.tmm.kmmgradle.generator.ohos.OhosStringsGenerator
import com.tencent.tmm.kmmgradle.utils.removeLineWraps
import org.gradle.api.file.FileTree
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

typealias KeyType = String

abstract class StringsGenerator(
    private val stringsFileTree: FileTree,
    private val strictLineBreaks: Boolean
) : BaseGenerator<String>() {

    override val inputFiles: Iterable<File> get() = stringsFileTree.files
    override val resourceClassName =
        ClassName("com.tencent.tmm.kmmresource.resource", "StringResource")
    override val mrObjectName: String = "strings"

    override fun loadLanguageMap(): Map<LanguageType, Map<KeyType, String>> {
        return stringsFileTree.map { file ->
            val language: LanguageType = LanguageType.fromFileName(file.parentFile.name)
            val strings: Map<KeyType, String> = loadLanguageStrings(file)
            language to strings
        }.groupBy(keySelector = { it.first }, valueTransform = { it.second }).mapValues { value ->
            val maps = value.value
            maps.fold(mutableMapOf()) { result, keyValueMap ->
                result.putAll(keyValueMap)
                result
            }
        }
    }

    private fun loadLanguageStrings(stringsFile: File): Map<KeyType, String> {
        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val doc = dBuilder.parse(stringsFile)

        val stringNodes = doc.getElementsByTagName("string")
        val mutableMap = mutableMapOf<KeyType, String>()

        for (i in 0 until stringNodes.length) {
            val stringNode = stringNodes.item(i)
            val name = stringNode.attributes.getNamedItem("name").textContent
            val value = stringNode.textContent

            mutableMap[name] = if (strictLineBreaks) value else value.removeLineWraps()
        }

        val incorrectKeys = mutableMap.filter { it.key == it.value }.keys.toList()
        if (incorrectKeys.isNotEmpty()) {
            throw EqualStringKeysException(incorrectKeys)
        }

        return mutableMap
    }

    override fun getImports(): List<ClassName> = emptyList()

    class Feature(
        private val info: SourceInfo,
        private val iosBaseLocalizationRegion: String,
        private val strictLineBreaks: Boolean,
        private val mrSettings: MRGenerator.MRSettings
    ) : ResourceGeneratorFeature<StringsGenerator> {
        private val stringsFileTree =
            info.commonResources.matching { it.include("MR/**/strings*.xml") }

        override fun createCommonGenerator() =
            CommonStringsGenerator(stringsFileTree, strictLineBreaks)

        override fun createAndroidGenerator() = AndroidStringsGenerator(
            stringsFileTree = stringsFileTree,
            strictLineBreaks = strictLineBreaks,
            getAndroidRClassPackage = { requireNotNull(info.androidRClassPackage) },
            mrSettings
        )

        override fun createOhosGenerator(): StringsGenerator =
            OhosStringsGenerator(stringsFileTree, strictLineBreaks, mrSettings)

        override fun createIosGenerator(): StringsGenerator {
            return AppleStringsGenerator(
                stringsFileTree,
                mrSettings.isStrictLineBreaks,
                mrSettings.iosLocalizationRegion ?: "en"
            )
        }

    }
}
