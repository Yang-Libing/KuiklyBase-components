pluginManagement {
    repositories {
        mavenLocal()
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "knoi"
include(":knoi-annotation")
include(":knoi")
include(":knoi-processor")
include(":knoi-gradle-plugin")
//
include(":example:sample")
include(":example:sample-api")
include(":example:lib1")
include(":example:lib2")
include(":example:empty")
include(":example:knoiapp")
