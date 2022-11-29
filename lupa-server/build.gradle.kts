group = rootProject.group
version = rootProject.version

plugins {
    groovy
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.10" apply true
}

dependencies {

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

    implementation(project(":lupa-core"))
    implementation(project(":kotlin-analysers"))
}
