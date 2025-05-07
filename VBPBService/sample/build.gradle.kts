import org.jetbrains.kotlin.gradle.plugin.KotlinHierarchyTemplate
import org.jetbrains.kotlin.gradle.targets.native.tasks.PodGenTask

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.easyPublish)
    alias(libs.plugins.harmonyBuild)
    alias(libs.plugins.tencentTmmKnoiPlugin)
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


    iosX64()
    iosArm64()
    // FIXME: cinterop失败问题
//    cocoapods {
//        summary = "Some description for the Shared Module"
//        homepage = "Link to the Shared Module homepage"
//        version = "1.0"
//        ios.deploymentTarget = "12.0"
////        podfile = project.file("../iosApp/Podfile")
//        framework {
//            baseName = "sample"
//            isStatic = true
//        }
//        specRepos {
//            url("https://git.woa.com/TencentVideoShared/VideoBase.git")
//            url("https://git.woa.com/TencentVideoShared/VideoBase-snapshot.git")
//            url("https://mirrors.tencent.com/repository/generic/pod-go/T-CocoaPods/Specs/")
//        }
//        pod("VBTransportServiceiOS") {
//            version = "6.1.0"
//            extraOpts += listOf("-compiler-option", "-fmodules")
//        }
//        pod("TQUICiOS") {
//            version = "1.5.3"
//            linkOnly = true
//        }
//        pod("VBTQUIC") {
//            version = "2.2.9"
//            linkOnly = true
//        }
//        pod("VBAFNHttpDataRequest") {
//            version = "2.15.1"
//            linkOnly = true
//        }
//        pod("VBKVServiceiOS") {
//            version = "2.6.3"
//            linkOnly = true
//        }
//        pod("VBMMKV") {
//            version = "1.3.2"
//            linkOnly = true
//        }
//        pod("MMKVCore") {
//            version = "1.3.2"
//            linkOnly = true
//        }
//        pod("VBLogServiceiOS") {
//            version = "0.0.48"
//            linkOnly = true
//        }
//    }

    ohosArm64 {
        binaries.sharedLib {
            // ！！！请不要修改so 名字，存在其他 SO 需要链接 KN 的 so，固定为 libkn.so！！！
            baseName = "kn"
            linkerOpts("-L${rootDir}/shared-template/libs/", "-lpbcurlwrapper")
            export(project(":shared-template")) // 源码依赖
            export(libs.kotlin.stdlib.platform.ext) // maven依赖
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
                api(project(":shared-template"))
                // 公参协议
                implementation("com.tencent.qqlive.protocol.tmm.pb:pbdata:1.0.43")
                // PB Message
                implementation("com.squareup.wire.kmm:wire-runtime:4.6.10")
                // 跨端工具库
                implementation("com.tencent.tmm:tmm-platform-utils:0.2.47")
                // JSON 序列化
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.7")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
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

tasks.withType<PodGenTask>().configureEach {
    doLast {
        val file = project.buildDir.resolve("cocoapods/synthetic/ios/Podfile")
        try {
            val inputStream = file.inputStream()
            var podfileContext = inputStream.bufferedReader().use { it.readText() }
            podfileContext = podfileContext.replace(
                "use_frameworks!".toRegex(),
                "use_frameworks! :linkage => :static"
            )
            podfileContext = podfileContext.replace(
                "# Disable signing for all synthetic pods KT-54314".toRegex(),
                """
                |config.build_settings['CLANG_ALLOW_NON_MODULAR_INCLUDES_IN_FRAMEWORK_MODULES'] = 'YES'
                |config.build_settings['ARCHS[sdk=iphonesimulator*]'] = 'x86_64'
                |config.build_settings['ENABLE_BITCODE'] = 'NO'
                |config.build_settings['CODE_SIGN_IDENTITY'] = ''
                """.trimMargin()
            )
            file.writeText(podfileContext)
        } catch (_: Exception) {
        }
    }
}


