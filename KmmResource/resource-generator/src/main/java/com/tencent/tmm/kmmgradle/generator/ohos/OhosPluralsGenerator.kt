package com.tencent.tmm.kmmgradle.generator.ohos

import com.google.gson.Gson
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.KeyType
import com.tencent.tmm.kmmgradle.generator.LanguageType
import com.tencent.tmm.kmmgradle.generator.MRGenerator
import com.tencent.tmm.kmmgradle.generator.NOPObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.PluralMap
import com.tencent.tmm.kmmgradle.generator.PluralsGenerator
import com.tencent.tmm.kmmgradle.generator.ohos.data.OhosPluralInfo
import com.tencent.tmm.kmmgradle.generator.ohos.data.OhosPluralQuantity
import com.tencent.tmm.kmmgradle.generator.ohos.data.OhosPluralResource
import org.gradle.api.file.FileTree
import java.io.File

class OhosPluralsGenerator(
    pluralsFileTree: FileTree,
    strictLineBreaks: Boolean,
    private val mrSettings: MRGenerator.MRSettings
) : PluralsGenerator(
    pluralsFileTree = pluralsFileTree,
    strictLineBreaks = strictLineBreaks,
), ObjectBodyExtendable by NOPObjectBodyExtendable() {

    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyInitializer(key: String): CodeBlock? =
        CodeBlock.of("PluralsResource(resourceName = %S)", getResKey(key))

    override fun generateResources(
        resourcesGenerationDir: File,
        language: LanguageType,
        strings: Map<KeyType, PluralMap>
    ) {
        if (strings.isNullOrEmpty()) {
            return
        }

        // 创建资源目录
        val elementDir = File(resourcesGenerationDir, language.ohosResourcesDir).apply {
            mkdirs()
        }
        val pluralFile = File(elementDir, ELEMENT_PLURAL_FILE)

        // 生成鸿蒙json
        val ohosPluralResource = OhosPluralResource()
        strings.forEach { (key, pluralMap) ->
            val ohosPluralInfo = OhosPluralInfo()
            ohosPluralInfo.name = getResKey(key)
            pluralMap.forEach { (pluralKey, pluralString) ->
                val ohosPluralQuantity = OhosPluralQuantity()
                ohosPluralQuantity.quantity = pluralKey
                ohosPluralQuantity.value = pluralString

                ohosPluralInfo.value.add(ohosPluralQuantity)
            }

            ohosPluralResource.plural.add(ohosPluralInfo)
        }
        pluralFile.writeText(Gson().toJson(ohosPluralResource))
    }

    private fun getResKey(key: String): String = "${mrSettings.resPrefix}${key}"

    companion object {
        private const val ELEMENT_PLURAL_FILE = "plural.json"
    }
}