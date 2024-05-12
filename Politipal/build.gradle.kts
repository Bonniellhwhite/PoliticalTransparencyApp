buildscript {
    dependencies {
        //noinspection UseTomlInstead
        //classpath ("com.google.gms:google-services:4.3.10")

    }
}

plugins {
    alias(libs.plugins.gradle.versions)
    alias(libs.plugins.version.catalog.update)
    id("com.google.gms.google-services") version "4.3.8" apply false
}

apply("${project.rootDir}/buildscripts/toml-updater-config.gradle")
