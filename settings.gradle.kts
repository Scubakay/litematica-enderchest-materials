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
        vers("dev", "1.21.8")
        versions("1.21.6", "1.21.5")
        vcsVersion = "dev"
    }
    create(rootProject)
}

rootProject.name = "Litematica Enderchest Materials"