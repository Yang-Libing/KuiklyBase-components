/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.apple.action

import org.gradle.api.Task
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink

internal class CopyResourcesFromKLibsToFrameworkAction : CopyResourcesFromKLibsAction() {
    override fun execute(task: Task) {
        println("do action CopyResourcesFromKLibsToFrameworkAction")
        task as KotlinNativeLink

        val framework: Framework = task.binary as Framework

        try {
            framework.outputFile.listFiles()?.firstOrNull {
                it.name == "tmm-resources-apple"
            }?.deleteRecursively()
        } catch (e: Exception) {
        }

        copyResourcesFromLibraries(
            linkTask = task,
            project = task.project,
            outputDir = framework.outputFile
        )
    }
}
