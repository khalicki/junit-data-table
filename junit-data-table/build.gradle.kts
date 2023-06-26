plugins {
    `java-library`
    `maven-publish`
}

group = "io.github.khalicki"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

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
            pom {
                name.set("${project.group}:${project.name}")
                description.set("JUnit5 parameterized test helper for declaring test case arguments")
                url.set("https://github.com/khalicki/junit-data-table")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/license/mit/")
                    }
                }
                developers {
                    developer {
                        id.set("khalicki")
                        name.set("Kamil Halicki")
                        url.set("https://github.com/khalicki")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/khalicki/junit-data-table.git")
                    developerConnection.set("scm:git:ssh://github.com:khalicki/junit-data-table.git")
                    url.set("http://github.com/khalicki/junit-data-table/tree/main")
                }
            }
        }
    }

    repositories {
        maven {
            name = "local"
            url = uri(layout.buildDirectory.dir("maven-repository"))
        }
    }
}
