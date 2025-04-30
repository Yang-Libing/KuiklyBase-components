/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.apple.action

import org.gradle.api.Task
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink

internal class CopyResourcesFromKLibsToDirAction : CopyResourcesFromKLibsAction() {

    override fun execute(task: Task) {
        println("do action CopyResourcesFromKLibsToDirAction start")

        task as KotlinNativeLink

        val outputPath = (task.project.properties.get(TARGET_DIR) as? String) ?: return
        val outputFile = task.project.file(outputPath)
        println("do action CopyResourcesFromKLibsToDirAction $outputPath")

        try {
            outputFile.listFiles()?.firstOrNull {
                it.name == "tmm-resources-apple"
            }?.deleteRecursively()
        } catch (e: Exception) {
        }

        copyResourcesFromLibraries(
            linkTask = task,
            project = task.project,
            outputDir = outputFile
        )
    }

    companion object {
        const val TARGET_DIR = "resource.targetCopyDir"
    }

}
