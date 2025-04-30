package com.tencent.tmm.kmmgradle.utils

import com.android.build.gradle.internal.tasks.cleanUpTaskOutputs
import org.gradle.api.Task

object CommonUtils {

    fun setupTaskExecutable(task: Task) {
        task.onlyIf { true }
        task.outputs.cacheIf { false }
        task.outputs.upToDateWhen { false }
        task.cleanUpTaskOutputs()
    }
}