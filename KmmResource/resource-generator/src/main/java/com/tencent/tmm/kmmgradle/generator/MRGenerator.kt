/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.tencent.tmm.kmmgradle.generator

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeSpec
import com.tencent.tmm.kmmgradle.MRVisibility
import com.tencent.tmm.kmmgradle.tasks.GenerateMultiplatformResourcesTask
import com.tencent.tmm.kmmgradle.toModifier
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import java.io.File

abstract class MRGenerator(
    generatedDir: File,
    protected val sourceSet: SourceSet,
    protected val mrSettings: MRSettings,
    internal val generators: List<Generator>
) {

    internal val outputDir = File(generatedDir, sourceSet.name)
    protected open val sourcesGenerationDir
        get() = File(outputDir, "src")
    protected open val resourcesGenerationDir
        get() = File(outputDir, "res")

    protected open val assetsGenerationDir: File
        get() = File(outputDir, AssetsGenerator.ASSETS_DIR_NAME)

    init {
        setupGenerationDirs()
    }

    private fun setupGenerationDirs() {
        sourcesGenerationDir.mkdirs()
        sourceSet.addSourceDir(sourcesGenerationDir)

        resourcesGenerationDir.mkdirs()
        sourceSet.addResourcesDir(resourcesGenerationDir)

        assetsGenerationDir.mkdirs()
        sourceSet.addAssetsDir(assetsGenerationDir)
    }

    internal fun generate() {
        sourcesGenerationDir.deleteRecursively()
        resourcesGenerationDir.deleteRecursively()
        assetsGenerationDir.deleteRecursively()

        beforeMRGeneration()

        @Suppress("SpreadOperator")
        val mrClassSpec = TypeSpec.objectBuilder(mrSettings.className)
            .addModifiers(*getMRClassModifiers())
            .addModifiers(mrSettings.visibility.toModifier())

        generators.forEach { generator ->
            val builder = TypeSpec.objectBuilder(generator.mrObjectName)
                .addModifiers(mrSettings.visibility.toModifier())

            val fileResourceInterfaceClassName =
                ClassName("com.tencent.tmm.kmmresource.resource", "ResourceContainer")
            builder.addSuperinterface(fileResourceInterfaceClassName.parameterizedBy(generator.resourceClassName))

            mrClassSpec.addType(
                generator.generate(
                    assetsGenerationDir,
                    resourcesGenerationDir,
                    builder
                )
            )
        }

        processMRClass(mrClassSpec)

        val mrClass = mrClassSpec.build()

        val fileSpec = FileSpec.builder(mrSettings.packageName, mrSettings.className)
            .addType(mrClass)

        generators
            .flatMap { it.getImports() }
            .plus(getImports())
            .forEach { fileSpec.addImport(it.packageName, it.simpleName) }

        val file = fileSpec.build()
        file.writeTo(sourcesGenerationDir)

        afterMRGeneration()
    }

    fun apply(project: Project): GenerateMultiplatformResourcesTask {
        val name = sourceSet.name
        val genTaskName = "generateMR$name"
        val genTask = runCatching {
            project.tasks.getByName(genTaskName) as GenerateMultiplatformResourcesTask
        }.getOrNull() ?: project.tasks.create(
            genTaskName,
            GenerateMultiplatformResourcesTask::class.java
        ) {
            it.generator = this
        }

        project.tasks.withType<Jar>().configureEach {
            it.dependsOn(genTask)
        }
        project.tasks
            .matching { it.name.endsWith("SourcesJar") }
            .configureEach { it.dependsOn(genTask) }

        project.tasks
            .matching { it.name.contains("syncPodComposeResourcesForIos") }
            .configureEach { it.dependsOn(genTask) }

        project.tasks
            .withType<KotlinCompile<*>>()
            .configureEach { it.dependsOn(genTask) }


        apply(generationTask = genTask, project = project)

        return genTask
    }

    protected open fun beforeMRGeneration() = Unit
    protected open fun afterMRGeneration() = Unit

    protected abstract fun getMRClassModifiers(): Array<KModifier>
    protected abstract fun apply(generationTask: Task, project: Project)

    protected open fun processMRClass(mrClass: TypeSpec.Builder) {}
    protected open fun getImports(): List<ClassName> = emptyList()

    interface Generator : ObjectBodyExtendable {
        val mrObjectName: String
        val resourceClassName: ClassName
        val inputFiles: Iterable<File>

        fun generate(
            assetsGenerationDir: File,
            resourcesGenerationDir: File,
            objectBuilder: TypeSpec.Builder
        ): TypeSpec

        fun getImports(): List<ClassName>
    }

    interface SourceSet {
        val name: String

        fun addSourceDir(directory: File)
        fun addResourcesDir(directory: File)
        fun addAssetsDir(directory: File)
    }

    data class MRSettings(
        val packageName: String,
        val className: String,
        val visibility: MRVisibility,
        val resPrefix: String,
        val isStrictLineBreaks: Boolean,
        val generatedDir: File,
        val iosLocalizationRegion: String?,
        val commonSourceSet: KotlinSourceSet,
        val androidRClassPackage: String?
    )
}
