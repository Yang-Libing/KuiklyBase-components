/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.ohos

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.tencent.tmm.kmmgradle.generator.AssetsGenerator
import com.tencent.tmm.kmmgradle.generator.MRGenerator
import com.tencent.tmm.kmmgradle.generator.NOPObjectBodyExtendable
import com.tencent.tmm.kmmgradle.generator.ObjectBodyExtendable
import org.gradle.api.file.SourceDirectorySet
import java.io.File

class OhosAssetsGenerator(
    sourceDirectorySet: SourceDirectorySet,
    private val mrSettings: MRGenerator.MRSettings
) : AssetsGenerator(sourceDirectorySet), ObjectBodyExtendable by NOPObjectBodyExtendable() {
    override fun getClassModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyModifiers(): Array<KModifier> = arrayOf(KModifier.ACTUAL)

    override fun getPropertyInitializer(fileSpec: AssetSpecFile) =
        CodeBlock.of("AssetResource(path = %S)", "assets/${getAssetSpecPath(fileSpec)}")

    override fun generateResources(
        assetsGenerationDir: File,
        resourcesGenerationDir: File,
        files: List<AssetSpec>
    ) {
        if (files.isNullOrEmpty()) {
            return
        }

        val assetsDir = File(resourcesGenerationDir, HARMONY_ASSETS_DIR).apply {
            mkdirs()
        }
        files.forEach { assetSpec ->
            when (assetSpec) {
                is AssetSpecDirectory ->
                    generateResources(assetsDir, resourcesGenerationDir, assetSpec.assets)

                is AssetSpecFile ->
                    assetSpec.file.copyTo(File(assetsDir, getAssetSpecPath(assetSpec)))
            }
        }
    }

    private fun getAssetSpecPath(fileSpec: AssetSpecFile): String =
        "${mrSettings.resPrefix}${fileSpec.pathRelativeToBase}"

    companion object {
        private const val HARMONY_ASSETS_DIR = "rawfile/assets"
    }
}
