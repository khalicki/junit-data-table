# junit-data-table
JUnit5 parametrized test helper for declaring test case arguments

[![CI](https://github.com/khalicki/junit-data-table/actions/workflows/ci.yml/badge.svg)](https://github.com/khalicki/junit-data-table/actions/workflows/ci.yml)

## Quick start

To quickly start using JUnit Data Table follow these steps:

### Add dependency

For projects using Gradle add following dependency:
```kotlin
testImplementation("io.github.khalicki:junit-data-table:0.1.0-SNAPSHOT")
```

For parametrized tests JUnit requires `junit-jupiter-params` so if you don't have it already add also:
```kotlin
testImplementation("org.junit.jupiter:junit-jupiter-params:<version>")
```

Because `junit-data-table` is not yet released to maven central you need to add maven snapshots repository to your project.
```kotlin
repositories {
    mavenCentral()
    maven(
        uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    )
}
```

### Write test

Simplest Java test with data table looks like that:
```java
    @ParameterizedTest
    @DataTableSource({
        @Row({"a", "b", "ab"}),
        @Row({"d", "e", "de"})
    })
    public void shouldConcatStrings(String first, String second, String result) {
        assertEquals(result, first + second);
    }
```

Same test written in Kotlin looks like this:
```kotlin
    @ParameterizedTest
    @DataTableSource(
        Row("a", "b", "ab"),
        Row("d", "e", "de"),
    )
    fun `should concat strings`(first: String, second: String, result: String) {
        assertEquals(result, first + second)
    }
```

