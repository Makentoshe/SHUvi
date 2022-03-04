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
    implementation(project(dependency.module.monolith.service))
    implementation(project(dependency.module.monolith.database))
    implementation(project(dependency.module.monolith.repository))
    implementation(project(dependency.module.monolith.repositoryDevice))

    val koinVersion = dependency.version.koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-ktor:$koinVersion")

    val ktorVersion = dependency.version.ktor
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
}