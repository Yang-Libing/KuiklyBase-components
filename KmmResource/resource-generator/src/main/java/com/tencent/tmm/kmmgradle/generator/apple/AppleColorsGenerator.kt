/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.apple

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.ColorNode
import com.tencent.tmm.kmmgradle.generator.ColorsGenerator
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.apple.AppleMRGenerator.Companion.ASSETS_DIR_NAME
import com.tencent.tmm.kmmgradle.utils.ArgbColor
import com.tencent.tmm.kmmgradle.utils.Constants
import com.tencent.tmm.kmmgradle.utils.parseRgbaColor
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.gradle.api.file.FileTree
import java.io.File

class AppleColorsGenerator(
    colorsFileTree: FileTree
) : ColorsGenerator(colorsFileTree), ObjectBodyExtendable by AppleGeneratorHelper() {
    override fun getImports(): List<ClassName> = emptyList<ClassName>()

    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)
    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun generateResources(resourcesGenerationDir: File, colors: List<ColorNode>) {
        val assetsDirectory = File(resourcesGenerationDir, ASSETS_DIR_NAME)

        if (!assetsDirectory.exists()) {
            assetsDirectory.mkdirs()
        }

        colors.forEach { colorNode ->
            val assetDir = File(assetsDirectory, "${colorNode.name}.colorset")
            assetDir.mkdir()
            val contentsFile = File(assetDir, "Contents.json")
            contentsFile.createNewFile()

            val colorContentObj = run {
                @Suppress("MagicNumber")
                val singleColor =
                    parseRgbaColor(colorNode.singleColor!!.toLong(16))
                buildJsonArray {
                    add(buildColorIdiomJsonObj(singleColor))
                }
            }


            val resultObj = buildJsonObject {
                put("colors", colorContentObj)
                put(
                    "info", buildJsonObject {
                        put("author", "xcode")
                        put("version", 1)
                    })
            }
            contentsFile.writeText(resultObj.toString())
        }
    }

    private fun buildColorJsonObj(argbColor: ArgbColor): JsonObject = buildJsonObject {
        put("color-space", "srgb")
        put(
            "components", buildJsonObject {
                put("alpha", argbColor.a)
                put("red", argbColor.r)
                put("green", argbColor.g)
                put("blue", argbColor.b)
            })
    }

    private fun buildColorIdiomJsonObj(argbColor: ArgbColor): JsonObject = buildJsonObject {
        put("color", buildColorJsonObj(argbColor))
        put("idiom", "universal")
    }

    fun buildAppearancesIdiomJsonBlock(valueTag: String, argbColor: ArgbColor): JsonObject {
        return buildJsonObject {
            put(
                "appearances", buildJsonArray {
                    add(
                        buildJsonObject {
                            put("appearance", "luminosity")
                            put("value", valueTag)
                        })
                })
            put("color", buildColorJsonObj(argbColor))
            put("idiom", "universal")
        }
    }

    override fun getPropertyInitializer(color: ColorNode): CodeBlock {
        return CodeBlock.of(
            "ColorResource(name = %S, bundle = ${Constants.platformInitializeArgsIOS})", color.name
        )
    }
}
