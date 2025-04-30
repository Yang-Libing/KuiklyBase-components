/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.apple.task

import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink

internal open class CopyResourcesFromKLibsToFrameworkTask : CopyResourcesFromKLibsTask() {

    @get:Internal
    lateinit var linkTask: KotlinNativeLink

    @TaskAction
    fun execute() {
        println("do action CopyResourcesFromKLibsToFrameworkAction")

        val framework: Framework = linkTask.binary as Framework

        copyResourcesFromLibraries(
            linkTask = linkTask,
            project = linkTask.project,
            outputDir = framework.outputFile
        )
    }

    companion object {
        const val TASK_NAME = "copyResourcesFromKLibsToFrameworkTask"
    }
}
