/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator.apple.action

import com.tencent.tmm.kmmgradle.generator.apple.task.CopyResourcesFromKLibsTask
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.Task
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink
import java.io.File

internal abstract class CopyResourcesFromKLibsAction : Action<Task> {

    protected fun copyKlibsResourcesIntoFramework(linkTask: KotlinNativeLink) {
        CopyResourcesFromKLibsTask.copyKlibsResourcesIntoFramework(linkTask)
    }

    protected open fun copyResourcesFromLibraries(
        linkTask: KotlinNativeLink,
        project: Project,
        outputDir: File
    ) {
        CopyResourcesFromKLibsTask.copyResourcesFromLibraries(linkTask, project, outputDir)
    }
}
