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

    implementation(project(":database"))
    implementation(project(":service"))
    implementation(project(":entity"))
    implementation(project(":repository"))
    implementation(project(":repository-device-create"))

    val koinVersion = dependency.version.koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-ktor:$koinVersion")

    val ktorVersion = dependency.version.ktor
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
}