/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.utils

import com.tencent.tmm.kmmgradle.generator.MRGenerator
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.kotlin.dsl.withType
import org.gradle.language.jvm.tasks.ProcessResources

fun dependsOnProcessResources(
    project: Project,
    sourceSet: MRGenerator.SourceSet,
    task: Task,
    shouldExcludeGenerated: Boolean
) {
    project.tasks
        .matching { it.name == sourceSet.name.removeSuffix("Main") + "ProcessResources" }
        .withType<ProcessResources>()
        .configureEach { processResourcesTask ->
            processResourcesTask.exclude {
                val path: String = it.file.absolutePath
                if (shouldExcludeGenerated && path.contains("generated/tmm-res")) return@exclude true
                if (path.contains("resources/MR")) return@exclude true
                false
            }
            processResourcesTask.dependsOn(task)
        }
}
