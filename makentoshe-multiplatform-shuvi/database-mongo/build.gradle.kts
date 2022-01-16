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
    implementation(project(":entity"))

    val koinVersion = dependency.version.koin
    implementation("io.insert-koin:koin-core:$koinVersion")

    implementation("org.litote.kmongo:kmongo:4.4.0")
    implementation("org.litote.kmongo:kmongo-coroutine:4.4.0")
}