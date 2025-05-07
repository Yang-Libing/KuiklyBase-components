pluginManagement {
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
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
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

rootProject.name = "VBPBService"
include(":androidApp")
include(":shared-template")
include(":sample")
