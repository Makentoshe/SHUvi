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

    implementation(project(dependency.module.service))
    implementation(project(dependency.module.repository))
    implementation(project(dependency.module.repositoryHello))

    val koinVersion = dependency.version.koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-ktor:$koinVersion")
}