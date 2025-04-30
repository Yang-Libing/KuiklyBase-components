package com.tencent.tmm.kmmgradle.generator.ohos

import com.google.gson.Gson
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.KeyType
import com.tencent.tmm.kmmgradle.generator.LanguageType
import com.tencent.tmm.kmmgradle.generator.MRGenerator
import com.tencent.tmm.kmmgradle.generator.NOPObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.StringsGenerator
import com.tencent.tmm.kmmgradle.generator.ohos.data.OhosStringInfo
import com.tencent.tmm.kmmgradle.generator.ohos.data.OhosStringResource
import org.gradle.api.file.FileTree
import java.io.File

class OhosStringsGenerator(
    stringsFileTree: FileTree,
    strictLineBreaks: Boolean,
    private val mrSettings: MRGenerator.MRSettings
) : StringsGenerator(
    stringsFileTree = stringsFileTree,
    strictLineBreaks = strictLineBreaks,
), ObjectBodyExtendable by NOPObjectBodyExtendable() {

    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyInitializer(key: String) =
        CodeBlock.of("StringResource(resourceName = %S)", "${mrSettings.resPrefix}${key}")

    override fun generateResources(
        resourcesGenerationDir: File,
        language: LanguageType,
        strings: Map<KeyType, String>
    ) {
        if (strings.isNullOrEmpty()) {
            return
        }

        // 创建资源目录
        val elementDir = File(resourcesGenerationDir, language.ohosResourcesDir).apply {
            mkdirs()
        }

        // 生成鸿蒙json
        val stringFile = File(elementDir, ELEMENT_STRING_FILE)
        val ohosStringResource = OhosStringResource()
        strings.forEach { (key, value) ->
            val ohosStringInfo = OhosStringInfo()
            ohosStringInfo.name = getResKey(key)
            ohosStringInfo.value = value
            ohosStringResource.string.add(ohosStringInfo)
        }
        stringFile.writeText(Gson().toJson(ohosStringResource))
    }

    private fun getResKey(key: String): String = "${mrSettings.resPrefix}${key}"

    companion object {
        private const val ELEMENT_STRING_FILE = "string.json"
    }
}