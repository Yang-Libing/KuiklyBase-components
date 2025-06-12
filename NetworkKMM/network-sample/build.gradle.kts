import org.jetbrains.kotlin.gradle.plugin.KotlinHierarchyTemplate

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.tencent.kuiklybase.knoi.plugin")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    KotlinHierarchyTemplate.default

    androidTarget() {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    ohosArm64 {
        binaries.sharedLib {
            // ！！！请不要修改so 名字，存在其他 SO 需要链接 KN 的 so，固定为 libkn.so！！！
            baseName = "kn"
            linkerOpts("-L${rootDir}/network/libs/", "-lpbcurlwrapper", "-lc++_shared", "-lc++", "-lc++abi")
            export(project(":network")) // 源码依赖
            // export(libs.kotlin.stdlib.platform.ext)  // maven依赖
            val main by compilations.getting
        }
        binaries.all {
            // release debug 信息，方便 addr2line 行号
            freeCompilerArgs += "-Xadd-light-debug=enable"
        }
    }


    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":network"))
            }
        }

        val ohosArm64Main by getting {
            dependencies {
            }
        }
    }
}

android {
    namespace = "com.tencent.kmm.component.sample"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
    }
}