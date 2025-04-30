/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.tencent.tmm.kmmgradle.generator.android.AndroidFontsGenerator
import com.tencent.tmm.kmmgradle.generator.apple.AppleFontsGenerator
import com.tencent.tmm.kmmgradle.generator.common.CommonFontsGenerator
import com.tencent.tmm.kmmgradle.generator.ohos.OhosFontsGenerator
import org.gradle.api.file.FileTree
import java.io.File

abstract class FontsGenerator(
    private val inputFileTree: FileTree
) : MRGenerator.Generator {

    override val inputFiles: Iterable<File> get() = inputFileTree.files
    override val resourceClassName =
        ClassName("com.tencent.tmm.kmmresource.resource", "FontResource")
    override val mrObjectName: String = "fonts"

    override fun generate(
        assetsGenerationDir: File,
        resourcesGenerationDir: File,
        objectBuilder: TypeSpec.Builder
    ): TypeSpec {
        val fontFiles = inputFileTree.map {
            FontFile(
                key = it.nameWithoutExtension,
                file = it
            )
        }

        beforeGenerateResources(objectBuilder, fontFiles)
        val typeSpec = createTypeSpec(inputFileTree.sortedBy { it.name }, objectBuilder)
        generateResources(resourcesGenerationDir, fontFiles)
        return typeSpec
    }

    /*
    @param keys: names of files like anastasia-regular.ttf
     */
    private fun createTypeSpec(files: List<File>, objectBuilder: TypeSpec.Builder): TypeSpec {
        @Suppress("SpreadOperator")
        objectBuilder.addModifiers(*getClassModifiers())

        extendObjectBodyAtStart(objectBuilder)

        /*
        * 1. Group fileNames by family name (split('-').first())
        * 2. Generate subtype for each family `classBuilder.addType(...)`
        * 3. Generate properties in family subtype for each font style
        * */

        val familyGroups = files.groupBy { file ->
            file.nameWithoutExtension.substringBefore("-")
        }

        familyGroups.forEach { group ->
            // TODO Make pairs: "style name" - "font file"
            val stylePairs = group
                .value
                .map { it.nameWithoutExtension.substringAfter("-") to it }

            objectBuilder.addType(
                generateFontFamilySpec(
                    familyName = group.key,
                    fontStyleFiles = stylePairs
                )
            )
        }
        extendObjectBodyAtEnd(objectBuilder)
        return objectBuilder.build()
    }

    override fun getImports(): List<ClassName> = emptyList()

    @Suppress("SpreadOperator")
    private fun generateFontFamilySpec(
        familyName: String,
        fontStyleFiles: List<Pair<String, File>>
    ): TypeSpec {
        val spec = TypeSpec
            .objectBuilder(familyName)
            .addModifiers(*getClassModifiers())
        fontStyleFiles
            .forEach { (styleName, file) ->
                val styleProperty = PropertySpec
                    .builder(styleName.decapitalize(), resourceClassName)
                    .addModifiers(*getPropertyModifiers())
                getPropertyInitializer(file)?.let { codeBlock ->
                    styleProperty.initializer(codeBlock)
                }
                spec.addProperty(styleProperty.build())
            }
        return spec.build()
    }

    protected open fun generateResources(
        resourcesGenerationDir: File,
        files: List<FontFile>
    ) {
    }

    abstract fun getClassModifiers(): Array<KModifier>

    abstract fun getPropertyModifiers(): Array<KModifier>

    abstract fun getPropertyInitializer(fontFile: File): CodeBlock?

    open fun beforeGenerateResources(objectBuilder: TypeSpec.Builder, files: List<FontFile>) {}

    data class FontFile(
        val key: String,
        val file: File
    )

    class Feature(
        private val info: SourceInfo,
        private val mrSettings: MRGenerator.MRSettings
    ) : ResourceGeneratorFeature<FontsGenerator> {
        private val stringsFileTree = info.commonResources.matching {
            it.include("MR/fonts/**.ttf", "MR/fonts/**.otf")
        }

        override fun createCommonGenerator() = CommonFontsGenerator(stringsFileTree)

        override fun createAndroidGenerator() = AndroidFontsGenerator(
            inputFileTree = stringsFileTree,
            getAndroidRClassPackage = { requireNotNull(info.androidRClassPackage) },
            mrSettings
        )

        override fun createOhosGenerator(): FontsGenerator =
            OhosFontsGenerator(stringsFileTree, mrSettings)

        override fun createIosGenerator(): FontsGenerator = AppleFontsGenerator(stringsFileTree)

    }
}
