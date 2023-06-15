plugins {
    id("java-library")
}

repositories {
    mavenCentral()
}

group = "io.github.khalicki"
version = "0.1.0-SNAPSHOT"

dependencies {
    compileOnly("org.junit.jupiter:junit-jupiter-params:5.9.2")

    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.compileJava {
    options.release.set(8)
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
        events("standardOut", "passed", "skipped", "failed")
    }
}
