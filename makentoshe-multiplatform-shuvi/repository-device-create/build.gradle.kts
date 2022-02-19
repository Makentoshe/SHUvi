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
    implementation(project(dependency.module.entity))
    implementation(project(dependency.module.repository))
    implementation(project(dependency.module.database))

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