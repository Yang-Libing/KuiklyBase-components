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
    // 使用默认层级结构模板
    KotlinHierarchyTemplate.default

    // Android平台
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }

        // 添加以下配置，将Android平台打包到组件产物中
        publishAllLibraryVariants()
        publishLibraryVariantsGroupedByFlavor = true
        publishLibraryVariants("release", "debug")
    }

    // iOS平台
    iosX64()
    iosArm64()
    // 与主端保持一致，默认只支持双架构
//    iosSimulatorArm64()
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "12.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared_template"
            isStatic = true
        }

        specRepos {
            url("https://git.woa.com/TencentVideoShared/VideoBase.git")
            url("https://git.woa.com/TencentVideoShared/VideoBase-snapshot.git")
            url("https://mirrors.tencent.com/repository/generic/pod-go/T-CocoaPods/Specs/")
        }

        pod("VBTransportServiceiOS") {
//            version = "6.1.0"
            source = git("https://git.woa.com/NextLib/VBTransportServiceiOS.git") {
                branch = "feature/sse_log"
            }
            extraOpts += listOf("-compiler-option", "-fmodules")
        }


        pod("TQUICiOS") {
            version = "1.5.3"
            linkOnly = true
        }
        pod("VBTQUIC") {
            version = "2.2.9"
            linkOnly = true
        }
        pod("VBAFNHttpDataRequest") {
            version = "2.15.1"
            linkOnly = true
        }
        pod("VBKVServiceiOS") {
            version = "2.6.3"
            linkOnly = true
        }
        pod("VBMMKV") {
            version = "1.3.2"
            linkOnly = true
        }
        pod("MMKVCore") {
            version = "1.3.2"
            linkOnly = true
            extraOpts += listOf("-compiler-option", "-fmodules")
        }
        pod("VBLogServiceiOS") {
            version = "0.0.48"
            linkOnly = true
        }
    }

    // Harmony Native平台
    ohosArm64 {
        val main by compilations.getting

        // 需要在shared里调来自外部C的时候加这个
        val interop by main.cinterops.creating {
            includeDirs("src/nativeInterop/cinterop/include")
        }

        compilations.forEach {
            // 抑制 NativeApi 提示
            it.compilerOptions.options.optIn.addAll(
                "kotlinx.cinterop.ExperimentalForeignApi",
                "kotlin.experimental.ExperimentalNativeApi",
            )
        }
    }

    sourceSets {
        val androidMain by getting {
            dependencies {
                // Android原生网络组件
                implementation("com.tencent.qqlive.modules:transportservice:0.0.5.54")
                implementation("com.tencent.qqlive.modules:pbservice_for_kmm:0.0.4.9.2")
                // RAFT
                implementation("com.tencent.raft:framework:0.5.2.9")
                implementation("com.squareup.okhttp3:okhttp-sse:4.12.0")
            }
        }

        // TODO: 改为统一依赖管理
        val commonMain by getting {
            dependencies {
                // put your multiplatform dependencies here
                api(libs.kotlin.stdlib.platform.ext)
                // JSON 序列化
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.7")
                // 协程
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:2.0.21.3-kn")
                // 原子操作
                implementation("org.jetbrains.kotlinx:atomicfu:0.24.1")
                // 公参协议
                implementation("com.tencent.qqlive.protocol.tmm.pb:pbdata:1.0.43")
                // PB Message
                implementation("com.squareup.wire.kmm:wire-runtime:4.6.10")
                // 字节数组操作工具
                implementation("com.ditchoom:buffer:1.4.0.1")
                // 并发集合
                implementation("com.tencent.tmm:stately-concurrency:0.0.16.4")
                implementation("com.tencent.tmm:stately-concurrent-collections:0.0.16.4")
                // 日期
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")
                implementation(libs.tmm.requestdegradation)
                // transport
                implementation("com.tencent.tmm:transportservice:0.0.9")
                implementation("com.tencent.tmm:tmm-platform-utils:0.2.47")
                // TODO: 直接依赖三端一码日志，避免多端分别注入
            }
        }
        //TODO: 补充KMP标准Test流程
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                // JSON 序列化
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.6-dev")
            }
        }

        val ohosArm64Main by getting {
            dependencies {
            }
        }
    }
}

android {
    namespace = "com.tencent.qqlive.kmm.vbpbservice"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
    }
    packagingOptions {
        pickFirst("lib/x86/libc++_shared.so")
        pickFirst("lib/x86_64/libc++_shared.so")
        pickFirst("lib/armeabi-v7a/libc++_shared.so")
        pickFirst("lib/arm64-v8a/libc++_shared.so")
        pickFirst("lib/armeabi/libc++_shared.so")
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


