pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.kikugie.dev/snapshots")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.7-beta.3"
}

stonecutter {
    kotlinController = true
    centralScript = "build.gradle.kts"

    shared {
        vers("dev", "1.19.4")
        versions("0.19.52", "0.22.0")
        vcsVersion = "dev"
    }
    create(rootProject)
}

rootProject.name = "Litematica Enderchest Materials"