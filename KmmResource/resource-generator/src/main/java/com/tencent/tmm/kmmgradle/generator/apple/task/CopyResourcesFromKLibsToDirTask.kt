/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.apple.task

import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink

internal open class CopyResourcesFromKLibsToDirTask : CopyResourcesFromKLibsTask() {

    @get:Internal
    lateinit var linkTask: KotlinNativeLink


    @TaskAction
    fun execute() {
        println("do action CopyResourcesFromKLibsToDirAction start")

        val task = linkTask as KotlinNativeLink

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
        const val TASK_NAME = "copyResourcesFromKLibsToDirTask"
    }
}
