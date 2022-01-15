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
    val ktorVersion = dependency.version.ktor
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
    implementation("io.ktor:ktor-server-cio:$ktorVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")

    val logbackVersion = dependency.version.logback
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    val kotlinJunitVersion = dependency.version.kotlin
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinJunitVersion")
}