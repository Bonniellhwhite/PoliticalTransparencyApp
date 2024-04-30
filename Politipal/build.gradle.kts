buildscript {
    dependencies {
        classpath(libs.google.services)

    }
}

plugins {
    alias(libs.plugins.gradle.versions)
    alias(libs.plugins.version.catalog.update)
}

apply("${project.rootDir}/buildscripts/toml-updater-config.gradle")
