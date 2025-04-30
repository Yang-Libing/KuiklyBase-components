plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidLibrary).apply(false)
    kotlin("multiplatform").version("2.0.21-mini-005").apply(false)
    alias(libs.plugins.composeOhos).apply(false)
    id("com.google.devtools.ksp") version "2.0.21-1.0.28" apply false
    id("com.tencent.tmm.knoi.plugin") version ("0.3.0") apply false
    id("easy-publish").version("1.5.0.15").apply(false)
    id("harmony-build").version("0.0.12").apply(false)
    id("tmm-resource-generator").version("0.1.9").apply(false)
    alias(libs.plugins.jetbrainsKotlinJvm) apply false

}

allprojects {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }


}



buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}