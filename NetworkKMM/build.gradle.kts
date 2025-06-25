plugins {
    // trick: for the same plugin versions in all sub-modules
    // 版本使用 libs.versions.toml 文件进行管理，在 /gradle 目录下
    id("com.android.application").version("8.1.0").apply(false)
    id("com.android.library").version("8.1.0").apply(false)
    kotlin("multiplatform").version("2.0.21-KBA-003").apply(false)
    id("com.google.devtools.ksp") version "2.0.21-1.0.28" apply false
    id("com.tencent.kuiklybase.knoi.plugin").version("0.0.4").apply(false)
}

allprojects {
    group = project.findProperty("gruopID") as String
    version = project.findProperty("mavenVersion") as String  // 确保所有模块继承此版本

    repositories {
        mavenLocal()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://mirrors.tencent.com/nexus/repository/maven-public/")

        google()
        mavenCentral()
    }
}

ext {
    group = "com.tencent.kuiklybase"
    version = "1.0.0"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
