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

    implementation(project(dependency.module.common))
    implementation(project(dependency.module.database))
    implementation(project(dependency.module.service))
    implementation(project(dependency.module.entity))
    implementation(project(dependency.module.repository))
    implementation(project(dependency.module.repositoryDeviceCreate))
    implementation(project(dependency.module.repositoryCrossrefSensorDeviceCreate))

    val koinVersion = dependency.version.koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-ktor:$koinVersion")


    val ktorVersion = dependency.version.ktor
    implementation("io.ktor:ktor-html-builder:$ktorVersion")

    // https://github.com/mockk/mockk
    testImplementation(dependency.library.mockk)

    testImplementation(dependency.library.junitApi)
    testRuntimeOnly(dependency.library.junitEngine)
}

tasks {
    test {
        useJUnitPlatform()
    }
}