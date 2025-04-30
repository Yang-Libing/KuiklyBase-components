/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.android

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.AssetsGenerator
import com.tencent.tmm.kmmgradle.generator.MRGenerator
import com.tencent.tmm.kmmgradle.generator.NOPObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import org.gradle.api.file.SourceDirectorySet
import java.io.File

class AndroidAssetsGenerator(
    sourceDirectorySet: SourceDirectorySet,
    private val mrSettings: MRGenerator.MRSettings
) : AssetsGenerator(sourceDirectorySet), ObjectBodyExtendable by NOPObjectBodyExtendable() {

    override fun generateResources(
        assetsGenerationDir: File,
        resourcesGenerationDir: File,
        files: List<AssetSpec>
    ) {
        files.forEach { assetSpec ->
            when (assetSpec) {
                is AssetSpecDirectory ->
                    generateResources(assetsGenerationDir, resourcesGenerationDir, assetSpec.assets)

                is AssetSpecFile ->
                    assetSpec.file.copyTo(File(assetsGenerationDir, getAssetResName(assetSpec)))
            }
        }
    }

    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyInitializer(fileSpec: AssetSpecFile) =
        CodeBlock.of("AssetResource(path = %S)", getAssetResName(fileSpec))

    private fun getAssetResName(fileSpec: AssetSpecFile) =
        "${mrSettings.resPrefix}${fileSpec.pathRelativeToBase}"
}
