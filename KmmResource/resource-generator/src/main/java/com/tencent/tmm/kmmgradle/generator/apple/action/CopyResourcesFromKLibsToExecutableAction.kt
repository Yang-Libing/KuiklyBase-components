/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.apple.action

import org.gradle.api.Task
import org.jetbrains.kotlin.gradle.plugin.mpp.AbstractExecutable
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink

internal class CopyResourcesFromKLibsToExecutableAction : CopyResourcesFromKLibsAction() {
    override fun execute(task: Task) {
        println("do action CopyResourcesFromKLibsToExecutableAction")

        task as KotlinNativeLink

        val executable: AbstractExecutable = task.binary as AbstractExecutable

        copyResourcesFromLibraries(
            linkTask = task,
            project = task.project,
            outputDir = executable.outputDirectory
        )
    }
}
