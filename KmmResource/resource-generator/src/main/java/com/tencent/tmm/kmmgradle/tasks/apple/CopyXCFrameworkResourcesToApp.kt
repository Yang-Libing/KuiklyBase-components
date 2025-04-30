package com.tencent.tmm.kmmgradle.tasks.apple
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.io.FileFilter

abstract class CopyXCFrameworkResourcesToApp : DefaultTask() {
    init {
        group = "tmm-resources"
    }

    @get:InputDirectory
    abstract val xcFrameworkDir: DirectoryProperty

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun copyResources() {
        val outputDir = outputDir.get().asFile

        val frameworkDir: File = xcFrameworkDir.get().asFile.walkTopDown()
            .first { it.extension == "framework" }

        frameworkDir.listFiles(FileFilter { it.extension == "bundle" })?.forEach {
            logger.info("copy resources bundle $it to $outputDir")
            it.copyRecursively(File(outputDir, it.name), overwrite = true)
        }
    }
}