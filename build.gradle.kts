import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.4.32"
    id("org.jetbrains.intellij") version "0.7.2"
    id("org.jetbrains.dokka") version "1.4.30"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "org.jetbrains.research.ml.kotlinAnalysis"
version = "1.0"

repositories {
    mavenCentral()
    jcenter()
    // Necessary for psiMiner
    maven(url = "https://dl.bintray.com/egor-bogomolov/astminer")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.jetbrains.research.psiminer:psiminer") {
        version {
            branch = "master"
        }
    }
}

intellij {
    type = "IC"
    version = "2020.3.3"
    downloadSources = false
    setPlugins("com.intellij.java", "org.jetbrains.kotlin")
    updateSinceUntilBuild = true
}

ktlint {
    enableExperimentalRules.set(true)
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
    withType<org.jetbrains.intellij.tasks.BuildSearchableOptionsTask>()
        .forEach { it.enabled = false }
}