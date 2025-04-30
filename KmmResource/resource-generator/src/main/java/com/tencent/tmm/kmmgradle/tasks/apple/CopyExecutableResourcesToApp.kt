package com.tencent.tmm.kmmgradle.tasks.apple

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.jetbrains.kotlin.library.KotlinLibraryLayout
import org.jetbrains.kotlin.library.impl.KotlinLibraryLayoutImpl
import java.io.File
import java.io.FileFilter

internal fun File.toKonanFile(): org.jetbrains.kotlin.konan.file.File =
    org.jetbrains.kotlin.konan.file.File(this.path)

abstract class CopyExecutableResourcesToApp : DefaultTask() {

    @get:InputFiles
    @get:Classpath
    abstract val klibs: ConfigurableFileCollection

    @get:OutputDirectory
    abstract val outputDirectory: DirectoryProperty

    init {
        group = "tmm-resources"
    }

    @TaskAction
    fun copyResources() {
        val outputDir: File = outputDirectory.get().asFile

        klibs
            .filter { library -> library.extension == "klib" }
            .filter(File::exists)
            .forEach { inputFile ->
                val klibKonan: org.jetbrains.kotlin.konan.file.File = inputFile.toKonanFile()
                val klib = KotlinLibraryLayoutImpl(klib = klibKonan, component = "default")
                val layout: KotlinLibraryLayout = klib.extractingToTemp

                // extracting bundles
                layout
                    .resourcesDir
                    .absolutePath
                    .let(::File)
                    .listFiles(FileFilter { it.extension == "bundle" })
                    // copying bundles to app
                    ?.forEach {
                        logger.info("${it.absolutePath} copying to $outputDir")
                        it.copyRecursively(target = File(outputDir, it.name), overwrite = true)
                    }
            }
    }
}
