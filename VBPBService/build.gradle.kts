plugins {
    alias(libs.plugins.com.android.application).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    alias(libs.plugins.easyPublish).apply(false)
    alias(libs.plugins.harmonyBuild).apply(false)
    alias(libs.plugins.googleDevtoolsKsp).apply(false)
    alias(libs.plugins.tencentTmmKnoiPlugin).apply(false)
}

allprojects {
    repositories {
        mavenLocal()
        maven("https://mirrors.tencent.com/repository/maven/tencentvideo")
        maven("https://mirrors.tencent.com/repository/maven/tencentvideo-snapshot")
        maven("https://mirrors.tencent.com/repository/maven/tmm-snapshot")
        maven("https://mirrors.tencent.com/repository/maven/tencent_public")
        maven("https://mirrors.tencent.com/nexus/repository/maven-public")
        maven("https://mirrors.tencent.com/repository/maven/thirdparty")
        maven("https://mirrors.tencent.com/repository/maven/thirdparty-snapshots")

        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
