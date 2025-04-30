package com.tencent.tmm.kmmgradle.configuration

import com.tencent.tmm.kmmgradle.generator.MRGenerator
import com.tencent.tmm.kmmgradle.generator.ResourceGeneratorFeature
import com.tencent.tmm.kmmgradle.generator.ohos.OhosMRGenerator
import com.tencent.tmm.kmmgradle.utils.getDependedFrom
import com.tencent.tmm.kmmgradle.utils.isDependsOn
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.KotlinTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import java.io.File

fun setupOhosGenerator(
    commonSourceSet: KotlinSourceSet,
    targets: List<KotlinTarget>,
    generatedDir: File,
    mrSettings: MRGenerator.MRSettings,
    features: List<ResourceGeneratorFeature<out MRGenerator.Generator>>,
    target: Project
) {
    val compilations = targets
        .filterIsInstance<KotlinNativeTarget>()
        .filter { it.name == "ohosArm64" }
        .map { kotlinNativeTarget ->
            kotlinNativeTarget.compilations
                .getByName(KotlinCompilation.MAIN_COMPILATION_NAME)
        }

    val defSourceSets = compilations.map { it.defaultSourceSet }
        .filter { it.isDependsOn(commonSourceSet) }
    compilations.forEach { compilation ->
        val kss = compilation.defaultSourceSet
        val depend = kss.getDependedFrom(defSourceSets)

        val sourceSet = createSourceSet(depend ?: kss)
        OhosMRGenerator(
            generatedDir = generatedDir,
            sourceSet = sourceSet,
            mrSettings = mrSettings,
            generators = features.map { it.createOhosGenerator() },
            compilation = compilation
        ).apply(target)
    }
}