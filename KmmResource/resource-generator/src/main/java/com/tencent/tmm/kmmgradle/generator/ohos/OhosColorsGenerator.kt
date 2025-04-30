/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.ohos

import com.google.gson.Gson
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.ColorNode
import com.tencent.tmm.kmmgradle.generator.ColorsGenerator
import com.tencent.tmm.kmmgradle.generator.MRGenerator
import com.tencent.tmm.kmmgradle.generator.NOPObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.ohos.data.OhosColorInfo
import com.tencent.tmm.kmmgradle.generator.ohos.data.OhosColorResource
import org.gradle.api.file.FileTree
import java.io.File

class OhosColorsGenerator(
    colorsFileTree: FileTree,
    private val mrSettings: MRGenerator.MRSettings
) : ColorsGenerator(colorsFileTree), ObjectBodyExtendable by NOPObjectBodyExtendable() {

    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyInitializer(color: ColorNode): CodeBlock =
        CodeBlock.of("ColorResource(resourceName = %S)", getColorName(color))

    override fun getImports() = emptyList<ClassName>()

    override fun generateResources(resourcesGenerationDir: File, colors: List<ColorNode>) {
        if (colors.isNullOrEmpty()) {
            return
        }

        // 构建color资源数据结构
        val singleColorResource = OhosColorResource()
        val lightColorResource = OhosColorResource()
        val darkColorResource = OhosColorResource()

        colors.forEach { colorNode ->
            if (colorNode.isThemed()) {
                // light color
                if (!colorNode.lightColor.isNullOrEmpty()) {
                    val lightColorInfo = OhosColorInfo()
                    lightColorInfo.name = getColorName(colorNode)
                    lightColorInfo.value = "#${replaceColorAlpha(colorNode.lightColor)}"
                    lightColorResource.color.add(lightColorInfo)
                }
                // dark color
                if (!colorNode.darkColor.isNullOrEmpty()) {
                    val darkColorInfo = OhosColorInfo()
                    darkColorInfo.name = getColorName(colorNode)
                    darkColorInfo.value = "#${replaceColorAlpha(colorNode.darkColor)}"
                    darkColorResource.color.add(darkColorInfo)
                }
            } else {
                // single color
                val colorInfo = OhosColorInfo()
                colorInfo.name = getColorName(colorNode)
                colorInfo.value = "#${replaceColorAlpha(colorNode.singleColor)}"
                singleColorResource.color.add(colorInfo)
            }
        }

        // 创建鸿蒙color资源文件
        if (singleColorResource.color.isNotEmpty()) {
            val colorDir = File(resourcesGenerationDir, BASE_ELEMENT_DIR).apply {
                mkdirs()
            }
            val colorFile = File(colorDir, COLORS_JSON_FILE_NAME)
            colorFile.writeText(Gson().toJson(singleColorResource))
        }
        if (lightColorResource.color.isNotEmpty()) {
            val lightElementDir = File(resourcesGenerationDir, LIGHT_ELEMENT_DIR).apply {
                mkdirs()
            }
            val lightColorFile = File(lightElementDir, COLORS_JSON_FILE_NAME)
            lightColorFile.writeText(Gson().toJson(lightColorResource))
        }
        if (darkColorResource.color.isNotEmpty()) {
            val darkElementDir = File(resourcesGenerationDir, DARK_ELEMENT_DIR).apply {
                mkdirs()
            }
            val darkColorFile = File(darkElementDir, COLORS_JSON_FILE_NAME)
            darkColorFile.writeText(Gson().toJson(darkColorResource))
        }
    }

    private fun getColorName(color: ColorNode): String = "${mrSettings.resPrefix}${color.name}"

    companion object {
        private const val BASE_ELEMENT_DIR = "base/element"
        private const val LIGHT_ELEMENT_DIR = "light/element"
        private const val DARK_ELEMENT_DIR = "dark/element"
        private const val COLORS_JSON_FILE_NAME = "color.json"
    }
}
