/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.apple

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.AssetsGenerator
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import com.tencent.tmm.kmmgradle.utils.Constants
import org.gradle.api.file.SourceDirectorySet
import java.io.File

class AppleAssetsGenerator(
    sourceDirectorySet: SourceDirectorySet
) : AssetsGenerator(sourceDirectorySet), ObjectBodyExtendable by AppleGeneratorHelper() {

    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyInitializer(fileSpec: AssetSpecFile): CodeBlock {
        val ext = fileSpec.file.extension

        val relativePathWithoutExt = fileSpec
            .pathRelativeToBase
            .replace('/', PATH_DELIMITER)
            .substringBeforeLast('.')

        return CodeBlock.of(
            "AssetResource(originalPath = %S, fileName = %S, extension = %S, bundle = %L)",
            fileSpec.pathRelativeToBase,
            relativePathWithoutExt,
            ext,
            Constants.platformInitializeArgsIOS
        )
    }

    override fun generateResources(
        assetsGenerationDir: File,
        resourcesGenerationDir: File,
        files: List<AssetSpec>
    ) {
        files.forEach { assetSpec ->
            when (assetSpec) {
                is AssetSpecDirectory ->
                    generateResources(assetsGenerationDir, resourcesGenerationDir, assetSpec.assets)

                is AssetSpecFile -> {
                    val newName = assetSpec.pathRelativeToBase.replace('/', PATH_DELIMITER)
                    assetSpec.file.copyTo(File(resourcesGenerationDir, newName))
                }
            }
        }
    }
}
