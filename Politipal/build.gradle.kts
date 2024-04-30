buildscript {
    dependencies {
        //noinspection UseTomlInstead
        //classpath ("com.google.gms:google-services:4.3.9")
    }
}

plugins {
    alias(libs.plugins.gradle.versions)
    alias(libs.plugins.version.catalog.update)
    id("com.google.gms.google-services") version "4.3.9" apply false
}

apply("${project.rootDir}/buildscripts/toml-updater-config.gradle")
