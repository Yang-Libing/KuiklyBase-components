package com.tencent.tmm.kmmgradle.configuration

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.api.AndroidSourceDirectorySet
import com.android.build.gradle.api.AndroidSourceSet
import com.tencent.tmm.kmmgradle.generator.MRGenerator
import com.tencent.tmm.kmmgradle.generator.ResourceGeneratorFeature
import com.tencent.tmm.kmmgradle.generator.android.AndroidMRGenerator
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.SourceSet
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.KotlinTarget
import org.jetbrains.kotlin.gradle.plugin.sources.android.androidSourceSetInfoOrNull
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

internal fun configureAndroidTargetGenerator(
    target: KotlinTarget,
    settings: MRGenerator.MRSettings,
    kotlinSourceSet: KotlinSourceSet,
    features: List<ResourceGeneratorFeature<out MRGenerator.Generator>>
) {
    val project: Project = target.project

    listOf(
        "com.android.library",
        "com.android.application"
    ).forEach { id ->
        project.plugins.withId(id) {
            setupAndroidGenerator(
                settings = settings,
                features = features,
                sourceSet = kotlinSourceSet,
                project = project
            )
        }
    }
}

internal fun Project.getAndroidRClassPackage(): String? {
    // before call android specific classes we should ensure that android plugin in classpath at all
    // it's required to support gradle projects without android target
    val isAndroidEnabled = listOf(
        "com.android.library",
        "com.android.application"
    ).any { project.plugins.findPlugin(it) != null }
    if (!isAndroidEnabled) return null

    val androidExt: BaseExtension = project.extensions.findByType()
        ?: return null
    return androidExt.namespace ?: getAndroidPackage(androidExt.mainSourceSet.manifest.srcFile)
}

@Suppress("LongParameterList")
private fun setupAndroidGenerator(
    project: Project,
    settings: MRGenerator.MRSettings,
    sourceSet: KotlinSourceSet,
    features: List<ResourceGeneratorFeature<out MRGenerator.Generator>>,
) {

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    val androidSourceSet: AndroidSourceSet =
        project.getAndroidSourceSetOrNull(sourceSet) ?: return

    println("Kmm Resource setupAndroidGenerator start!!!")

    setAssetsDirsRefresh(project)

    AndroidMRGenerator(
        generatedDir = settings.generatedDir,
        sourceSet = createSourceSet(androidSourceSet, sourceSet),
        settings = settings,
        generators = features.map { it.createAndroidGenerator() },
    ).apply(project)
}

private fun setAssetsDirsRefresh(project: Project) {
    // without this code Android Gradle Plugin not copy assets to aar
    project.tasks
        .matching { it.name.startsWith("package") && it.name.endsWith("Assets") }
        .configureEach { task ->
            // for gradle optimizations we should use anonymous object
            @Suppress("ObjectLiteralToLambda")
            task.doFirst(object : Action<Task> {
                override fun execute(t: Task) {
                    val android: BaseExtension = project.extensions.getByType()
                    val assets: AndroidSourceDirectorySet = android.mainSourceSet.assets
                    assets.setSrcDirs(assets.srcDirs)
                }
            })
        }
}

private fun getAndroidPackage(manifestFile: File): String {
    val dbFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
    val dBuilder: DocumentBuilder = dbFactory.newDocumentBuilder()
    val doc: Document = dBuilder.parse(manifestFile)

    val manifestNodes: NodeList = doc.getElementsByTagName("manifest")
    val manifest: Node = manifestNodes.item(0)

    return manifest.attributes.getNamedItem("package").textContent
}

private val BaseExtension.mainSourceSet: AndroidSourceSet
    get() = this.sourceSets.getByName(SourceSet.MAIN_SOURCE_SET_NAME)


private fun createSourceSet(
    androidSourceSet: AndroidSourceSet,
    kotlinSourceSet: KotlinSourceSet
): MRGenerator.SourceSet {
    return object : MRGenerator.SourceSet {
        override val name: String
            get() = "android${androidSourceSet.name.capitalize()}"

        override fun addSourceDir(directory: File) {
            kotlinSourceSet.kotlin.srcDir(directory)
            androidSourceSet.kotlin.srcDir(directory)
        }

        override fun addResourcesDir(directory: File) {
            androidSourceSet.res.srcDir(directory)
        }

        override fun addAssetsDir(directory: File) {
            androidSourceSet.assets.srcDir(directory)
        }
    }
}


/**
 * Replace of ExperimentalKotlinGradlePluginApi in AGP
 * Current realisation in plugin use of Deprecated version AndroidSourceSet
 */
@ExperimentalKotlinGradlePluginApi
internal fun Project.getAndroidSourceSetOrNull(kotlinSourceSet: KotlinSourceSet): AndroidSourceSet? {
    val androidSourceSetInfo = kotlinSourceSet.androidSourceSetInfoOrNull ?: return null
    val android = extensions.findByType<BaseExtension>() ?: return null
    return android.sourceSets.getByName(androidSourceSetInfo.androidSourceSetName)
}