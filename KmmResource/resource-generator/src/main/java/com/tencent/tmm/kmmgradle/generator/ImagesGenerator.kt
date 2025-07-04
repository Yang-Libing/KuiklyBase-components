/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.tencent.tmm.kmmgradle.generator.android.AndroidImagesGenerator
import com.tencent.tmm.kmmgradle.generator.apple.AppleImagesFilesGenerator
import com.tencent.tmm.kmmgradle.generator.common.CommonImagesGenerator
import com.tencent.tmm.kmmgradle.generator.ohos.OhosImagesGenerator
import com.tencent.tmm.kmmgradle.utils.withoutScale
import org.gradle.api.file.FileTree
import org.gradle.api.logging.Logger
import java.io.File

abstract class ImagesGenerator(
    private val inputFileTree: FileTree
) : MRGenerator.Generator {

    override val inputFiles: Iterable<File> get() = inputFileTree.files
    override val resourceClassName =
        ClassName("com.tencent.tmm.kmmresource.resource", "ImageResource")
    override val mrObjectName: String = "images"

    override fun generate(
        assetsGenerationDir: File,
        resourcesGenerationDir: File,
        objectBuilder: TypeSpec.Builder
    ): TypeSpec {
        val fileMap = inputFileTree.groupBy { file ->
            // SVGs do not have scale suffixes, so we need to remove the extension first
            val key = file
                .nameWithoutExtension
                .withoutScale

            "$key.${file.extension}"
        }

        beforeGenerateResources(objectBuilder, fileMap.keys.sorted())

        val typeSpec = createTypeSpec(fileMap.keys.sorted(), objectBuilder)

        generateResources(
            resourcesGenerationDir = resourcesGenerationDir,
            keyFileMap = fileMap.mapKeys { (key, _) ->
                key.substringBeforeLast(".") // Remove file extension from keys
            }
        )

        return typeSpec
    }

    @Suppress("SpreadOperator")
    fun createTypeSpec(fileNames: List<String>, objectBuilder: TypeSpec.Builder): TypeSpec {
        objectBuilder.addModifiers(*getClassModifiers())

        extendObjectBodyAtStart(objectBuilder)

        fileNames.forEach { fileName ->
            val updatedFileName = fileName.substringBeforeLast(".")
                .replace(".", "_") + ".${fileName.substringAfterLast(".")}"
            val propertyName = updatedFileName.substringBeforeLast(".")
            val property = PropertySpec.builder(propertyName, resourceClassName)

            property.addModifiers(*getPropertyModifiers())
            getPropertyInitializer(updatedFileName)?.let { property.initializer(it) }
            objectBuilder.addProperty(property.build())
        }

        extendObjectBodyAtEnd(objectBuilder)

        return objectBuilder.build()
    }

    override fun getImports(): List<ClassName> = emptyList()

    protected open fun beforeGenerateResources(
        objectBuilder: TypeSpec.Builder,
        keys: List<String>
    ) {
    }

    protected open fun generateResources(
        resourcesGenerationDir: File,
        keyFileMap: Map<String, List<File>>
    ) {
    }

    abstract fun getClassModifiers(): Array<KModifier>

    abstract fun getPropertyModifiers(): Array<KModifier>

    abstract fun getPropertyInitializer(fileName: String): CodeBlock?

    class Feature(
        private val info: SourceInfo,
        private val mrSettings: MRGenerator.MRSettings,
        private val logger: Logger
    ) : ResourceGeneratorFeature<ImagesGenerator> {
        private val stringsFileTree = info.commonResources.matching {
            it.include(
                "MR/images/**/*.png",
                "MR/images/**/*.jpg",
                "MR/images/**/*.svg",
                "MR/images/**/*.webp",
                "MR/images/**/*.gif"
            )
        }

        override fun createCommonGenerator() =
            CommonImagesGenerator(stringsFileTree)

        override fun createAndroidGenerator() = AndroidImagesGenerator(
            inputFileTree = stringsFileTree,
            getAndroidRClassPackage = { requireNotNull(info.androidRClassPackage) },
            logger = logger,
            mrSettings
        )

        override fun createOhosGenerator(): ImagesGenerator =
            OhosImagesGenerator(stringsFileTree, mrSettings, logger)

        override fun createIosGenerator(): ImagesGenerator =
            AppleImagesFilesGenerator(stringsFileTree)


    }
}
