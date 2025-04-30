/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction
import org.jetbrains.kotlin.konan.properties.Properties
import java.io.File
import java.io.FileFilter
import java.io.FileInputStream

open class CopyXCFrameworkResourcesToApp : DefaultTask() {
    init {
        group = "tmm-resources"
    }

    @InputDirectory
    lateinit var xcFrameworkDir: File

    @TaskAction
    fun copyResources() {
        val rootDir = project.rootDir
        // 构建 local.properties 文件的路径
        val localPropertiesFile = File(rootDir, "local.properties")
        val localProperties =  Properties()
        localProperties.load(FileInputStream(localPropertiesFile))

        val buildProductsDir = localProperties.getProperty("tmm.resources.BUILT_PRODUCTS_DIR") as String
        val contentsFolderPath = localProperties.getProperty("tmm.resources.CONTENTS_FOLDER_PATH") as String
        val outputDir = File("$buildProductsDir/$contentsFolderPath")

        val frameworkDir: File = xcFrameworkDir.walkTopDown().first { it.extension == "framework" }
        frameworkDir.listFiles(FileFilter { it.extension == "bundle" })?.forEach {
            project.logger.info("copy resources bundle $it to $outputDir")
            it.copyRecursively(File(outputDir, it.name), overwrite = true)
        }
    }
}
