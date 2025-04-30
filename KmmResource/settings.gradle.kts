enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
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
        mavenLocal()
        google()
        gradlePluginPortal()
        mavenCentral()

    }
}

rootProject.name = "KmmResource"
include(":resource-compose")
include(":resource-core")
//include(":resource-generator")
include(":sample")
include(":androidApp")
include(":sample_noohos")
//include(":sample_noohos_sub")
include(":shared-template")
includeBuild("resource-generator")
