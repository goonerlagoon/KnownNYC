// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false

    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0" apply false


//    id("com.android.application") version "8.1.2" apply false
//    id("org.jetbrains.kotlin.jvm") version "1.9.23"
//    id("org.jetbrains.kotlin.android")
}

