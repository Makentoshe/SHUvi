plugins {
    kotlin("jvm")
}

group = dependency.build.group
version = dependency.build.versionName

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(dependency.module.monolith.common))
    implementation(project(dependency.module.monolith.entity))
    implementation(project(dependency.module.monolith.repository))
    implementation(project(dependency.module.monolith.database))
}