plugins {
    kotlin("jvm") version "1.8.22"
    id("application")
}

version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")

    testImplementation(project(":junit-data-table"))
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
        events("standardOut", "passed", "skipped", "failed")
    }
}


