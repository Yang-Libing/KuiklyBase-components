plugins {
    id("com.android.application").version("8.1.0").apply(false)
    id("com.android.library").version("8.1.0").apply(false)
    kotlin("multiplatform").version("2.0.21-mini-005").apply(false)
    id("com.google.devtools.ksp") version "2.0.21-1.0.28" apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.tencent.tmm.knoi.plugin").version("0.2.18").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

allprojects {
    val VERSION: String by project
    val GROUP_ID: String by project
    group = GROUP_ID
    version = VERSION
}