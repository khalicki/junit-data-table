plugins {
    kotlin("jvm") version "1.9.23"
    id("application")
}

version = "0.1.0"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation(project(":junit-data-table"))
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
        events("standardOut", "passed", "skipped", "failed")
    }
}


