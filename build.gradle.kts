plugins {
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

group = "io.github.khalicki"
version = "0.1.0-SNAPSHOT"

nexusPublishing {
    repositories {
        create("s01Nexus") {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}
