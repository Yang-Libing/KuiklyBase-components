/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.ohos

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.ImagesGenerator
import com.tencent.tmm.kmmgradle.generator.MRGenerator
import com.tencent.tmm.kmmgradle.generator.NOPObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import com.tencent.tmm.kmmgradle.utils.svg
import org.gradle.api.file.FileTree
import org.gradle.api.logging.Logger
import java.io.File

class OhosImagesGenerator(
    inputFileTree: FileTree,
    private val mrSettings: MRGenerator.MRSettings,
    private val logger: Logger
) : ImagesGenerator(inputFileTree), ObjectBodyExtendable by NOPObjectBodyExtendable() {
    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyInitializer(fileName: String): CodeBlock {
        return CodeBlock.of(
            "ImageResource(drawableName = %S , suffix = %S)",
            getImageResName(fileName),
            getImageSuffix(fileName)
        )
    }

    override fun generateResources(
        resourcesGenerationDir: File,
        keyFileMap: Map<String, List<File>>
    ) {
        if (keyFileMap.isNullOrEmpty()) {
            return
        }

        keyFileMap.flatMap { (key, files) ->
            files.map { key to it }
        }.forEach { (key, file) ->
            val scale = file.nameWithoutExtension.substringAfter("@").substringBefore("x")
            val scaleName = when (scale) {
                "0.75" -> "sdpi"
                "1" -> "mdpi"
                "1.5" -> "ldpi"
                "2" -> "xldpi"
                "3" -> "xxldpi"
                "4" -> "xxxldpi"
                else -> if (file.svg) {
                    "base"
                } else {
                    logger.warn("image $file - unknown scale ($scale)")
                    throw RuntimeException("$file - unknown scale ($scale). Please check scale is configured. For example: ${scale}@1x.png")
                }

            }

            // 创建资源目录
            val mediaDir = File(resourcesGenerationDir, "${scaleName}/${HARMONY_MEDIA_DIR}").apply {
                mkdirs()
            }

            val resourceFile = File(mediaDir, "${mrSettings.resPrefix}${key}.${file.extension}")
            file.copyTo(resourceFile)
        }
    }

    private fun getImageResName(fileName: String): String {
        val processedKey = fileName.substringBefore(".")
        return "${mrSettings.resPrefix}${processedKey}"
    }

    companion object {
        private const val HARMONY_MEDIA_DIR = "media"

        fun getImageSuffix(fileName: String): String {
            val processedKey = fileName.substringAfter(".")
            return processedKey
        }
    }
}