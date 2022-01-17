plugins {
    application
    kotlin("jvm") version Dependency.version.kotlin
    kotlin("plugin.serialization") version Dependency.version.serialization
}

group = dependency.build.group
version = dependency.build.versionName

application {
    mainClass.set("com.makentoshe.ApplicationKt")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {
    implementation(project(":entity"))
    implementation(project(":service"))
    implementation(project(":service-hello"))
    implementation(project(":service-device"))
    implementation(project(":service-device-create"))
    implementation(project(":database-mongo"))

    val ktorVersion = dependency.version.ktor
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-server-cio:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")

    val serializationJsonVersion = dependency.version.serializationJson
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationJsonVersion")

    val koinVersion = dependency.version.koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-ktor:$koinVersion")

    val logbackVersion = dependency.version.logback
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    val kotlinJunitVersion = dependency.version.kotlin
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinJunitVersion")
}