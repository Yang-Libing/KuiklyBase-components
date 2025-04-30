import org.jetbrains.kotlin.konan.target.HostManager

plugins {
    kotlin("multiplatform")
    id("com.tencent.tmm.knoi.plugin")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class) kotlin {
    targetHierarchy.default()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    ohosArm64 {
        binaries.all {
            freeCompilerArgs += "-Xadd-light-debug=enable"
        }
    }

    sourceSets {
        val ohosArm64Main by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.3-kno")
            }
        }
        val commonTest by getting {
            dependencies {
                api(kotlin("test"))
            }
        }
    }
}

knoi {
    debug = true
}

