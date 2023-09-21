buildscript {
    dependencies {
        classpath (libs.moko.resources.generator)
//        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
    kotlin("plugin.serialization") version "1.8.0"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}