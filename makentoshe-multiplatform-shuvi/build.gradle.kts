plugins {
    application
    kotlin("jvm") version Dependency.version.kotlin
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
    implementation(project(":service"))
    implementation(project(":service-hello"))
    implementation(project(":service-device"))

    val ktorVersion = dependency.version.ktor
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-server-cio:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")

    val koinVersion = dependency.version.koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-ktor:$koinVersion")

    val logbackVersion = dependency.version.logback
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    val kotlinJunitVersion = dependency.version.kotlin
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinJunitVersion")
}