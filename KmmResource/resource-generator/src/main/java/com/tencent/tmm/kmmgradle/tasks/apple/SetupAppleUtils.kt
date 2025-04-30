/*
 * Copyright 2024 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.tasks.apple

import com.tencent.tmm.kmmgradle.generator.apple.action.CopyResourcesFromKLibsToExecutableAction
import com.tencent.tmm.kmmgradle.utils.klibs
import com.tencent.tmm.kmmgradle.utils.propertyString
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.mpp.AbstractExecutable
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.TestExecutable
import org.jetbrains.kotlin.konan.properties.Properties
import java.io.File
import java.io.FileInputStream

internal val Framework.nameWithoutBuildType: String
    get() {
        val buildType: String = this.buildType.name
        val nameWithoutFramework: String = this.name.removeSuffix("Framework")

        return nameWithoutFramework.substring(
            startIndex = 0,
            endIndex = nameWithoutFramework.length - buildType.length
        )
    }

internal fun Project.propertyStrings(name: String): List<String>? {
    val properties: String = propertyString(name) ?: return null

    return properties.split(" ")
}

internal fun setupExecutableResources(target: KotlinNativeTarget) {
    val project: Project = target.project
    val buildProductsDir =
        try {
            project.getLocalProp().getProperty("tmm.resources.BUILT_PRODUCTS_DIR") as String
        } catch (e: Exception) {
            null
        }
    val contentsFolderPath =
        try {
            project.getLocalProp()
                .getProperty("tmm.resources.CONTENTS_FOLDER_PATH") as String
        } catch (e: Exception) {
            null
        }
    if (buildProductsDir == null || contentsFolderPath == null) {
        return
    }
    target.binaries.withType<AbstractExecutable>().configureEach { executable ->
        val copyTaskName: String =
            executable.linkTaskProvider.name.replace("link", "copyResources")


        project.tasks.register<CopyExecutableResourcesToApp>(copyTaskName) {
            dependsOn(executable.linkTaskProvider)

            klibs.from(executable.linkTaskProvider.map { it.klibs })

            outputDirectory.set(run {
                File("$buildProductsDir/$contentsFolderPath")
            })
        }
    }
}

internal fun setupTestsResources(target: KotlinNativeTarget) {
    target.binaries.withType<TestExecutable>().configureEach { executable ->
        executable.linkTaskProvider.configure { link ->
            link.doLast(CopyResourcesFromKLibsToExecutableAction())
        }
    }
}

internal fun Project.getLocalProp(): Properties {
    val rootDir = rootDir
    // 构建 local.properties 文件的路径
    val localPropertiesFile = File(rootDir, "local.properties")
    val localProperties = Properties()
    localProperties.load(FileInputStream(localPropertiesFile))
    return localProperties
}