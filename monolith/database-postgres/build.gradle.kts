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
    implementation(project(dependency.module.monolith.database))
    implementation(project(dependency.module.monolith.entity))

    // https://github.com/JetBrains/Exposed
    val exposedVersion = dependency.version.exposed
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    val koinVersion = dependency.version.koin
    implementation("io.insert-koin:koin-core:$koinVersion")

    val pgjdbcVersion = "0.8.9"
    implementation("com.impossibl.pgjdbc-ng:pgjdbc-ng:$pgjdbcVersion")

    val postgresVersion = "42.3.2"
    implementation("org.postgresql:postgresql:$postgresVersion")

    val apacheCommonsVersion = "1.9"
    implementation("org.apache.commons:commons-text:$apacheCommonsVersion")
}