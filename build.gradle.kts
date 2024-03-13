val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val h2_version: String by project
val coroutines_version: String by project
val postgresql_version: String by project
val mysql_version: String by project
val mockk_version: String by project
val koin_version: String by project
val jupiter_version: String by project
val assertk_version: String by project

plugins {
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.8"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
}

group = "fr.rage.lafie"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    // KTor server
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-config-yaml:$ktor_version")

    // Requests Validation
    implementation("io.ktor:ktor-server-request-validation:$ktor_version")

    // Resources
    implementation("io.ktor:ktor-server-resources:$ktor_version")

    // Status pages
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")

    // Content negotiation
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")

    // Dependencies injection
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")

    // Database
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("com.h2database:h2:$h2_version")


    // Logging
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // Units tests
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.junit.jupiter:junit-jupiter:${jupiter_version}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")
    testImplementation("io.mockk:mockk:$mockk_version")
    testImplementation("io.insert-koin:koin-test:$koin_version")
    testImplementation("io.insert-koin:koin-test-junit5:$koin_version")
    testImplementation("com.willowtreeapps.assertk:assertk:$assertk_version")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

ktor {
    docker {
        jreVersion.set(JavaVersion.VERSION_17)
        localImageName.set("my-shopping-list-api")
        imageTag.set("1.0.0")
    }
}
