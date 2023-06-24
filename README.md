# JUnit5 Data Table
[![CI](https://github.com/khalicki/junit-data-table/actions/workflows/ci.yml/badge.svg)](https://github.com/khalicki/junit-data-table/actions/workflows/ci.yml)

The library for defining two-dimensional data table of arguments for parametrized tests in JUnit5. 

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

### Use data table to parametrize test

Add a `@DataTableSource` annotation to a test with JUnit's `@ParametrizedTest` annotation. A simple test with two test cases written in Java looks like that:
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
