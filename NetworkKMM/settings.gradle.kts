pluginManagement {
    repositories {
        mavenLocal()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://mirrors.tencent.com/nexus/repository/maven-public/")

        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://mirrors.tencent.com/nexus/repository/maven-public/")

        google()
        mavenCentral()
    }
}

rootProject.name = "NetworkKMM"
include(":androidApp")
include(":network")
include(":network-sample")
