plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

group = dependency.build.group
version = dependency.build.versionName

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(dependency.module.monolith.entity))
    implementation(project(dependency.module.monolith.common))

    val serializationJsonVersion = dependency.version.serializationJson
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationJsonVersion")

    val ktorVersion = dependency.version.ktor
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
}