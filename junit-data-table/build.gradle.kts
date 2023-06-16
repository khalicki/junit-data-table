plugins {
    `java-library`
    `maven-publish`
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
    withSourcesJar()
    withJavadocJar()
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

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
        }
    }

//    repositories {
//        maven {
//            name = "local"
//            url = uri(layout.buildDirectory.dir("maven-repository"))
//        }
//    }
}
