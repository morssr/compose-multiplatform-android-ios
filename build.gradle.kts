buildscript {
    dependencies {
        classpath (libs.moko.resources.generator)
//        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
    }
}

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    kotlin("plugin.serialization") version "1.8.0"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}