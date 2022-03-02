plugins {
    application
    kotlin("jvm") version Dependency.version.kotlin
    kotlin("plugin.serialization") version Dependency.version.serialization
    id(Dependency.build.plugins.shadowJar.id) version Dependency.build.plugins.shadowJar.version
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
    implementation(kotlin("stdlib"))

    implementation(project(dependency.module.monolith.entity))
    implementation(project(dependency.module.monolith.service))
    implementation(project(dependency.module.monolith.databaseMongo))

    implementation(project(dependency.module.monolith.serviceDevice))
    implementation(project(dependency.module.monolith.serviceDevices))
    implementation(project(dependency.module.monolith.serviceDeviceCreate))
    implementation(project(dependency.module.monolith.serviceDeviceDelete))

    implementation(project(dependency.module.monolith.serviceSensorGet))
    implementation(project(dependency.module.monolith.serviceSensorCreate))

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

tasks {
    shadowJar {
        archiveVersion.set("")
        archiveClassifier.set("shadow")
    }
}