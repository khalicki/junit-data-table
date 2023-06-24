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

## User guide

JUnit 5 supports parametrized tests as described in [Parameterized Tests](https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests) in JUnit documentation.
Each test is declared using `@ParametrizedTest` annotation and at least one *Arguments Source*. This library adds a new `Arguments Source` that has the ability to provide multiple arguments for each test invocation.

### DataTableSource

In order to pass arguments to parametrized test, a `@DataTableSource` annotation should be added to test method. The name of this annotation comes from [Data tables](https://spockframework.org/spock/docs/2.3/data_driven_testing.html#data-tables) concept from Spock framework. 

The `value` attribute of the annotation contain array of arguments for each test invocation. Each invocation is represented by `@Row` annotation. There should be at least one row defined in data table (needs implementation!).

#### Row

`Row` annotation represents single row in data table, and can be used only inside `@DataTableSource`. It is a single test invocation with arguments declared using `value` attribute. It's a default attribute so attribute name can be omitted.

```java
@Row({"hello", "world"})
```

To pass these values to test invocation, annotated method should have same number of arguments.
```java
public void someTest(String firstArgument, String secondArgument)
```

`Row` annotation only accepts arguments of type `java.lang.String`. If you need test arguments of different type you can use [JUnit Argument Conversion](https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-argument-conversion) which converts `String` values to declared arguments.

```java
@ParameterizedTest
@DataTableSource({
    @Row({"1", "1", "2"}),
    @Row({"2", "3", "5"})
})
public void shouldSumInts(int first, int second, int result) {
    assertEquals(result, first + second);
}
```

##### Null arguments

Java annotations doesn't allow passing `null` as annotation attribute. In order to allow `null` arguments all `@Row` arguments with string equal to `"null"` are converted to `null` value.

```java
@ParameterizedTest
@DataTableSource({
    @Row(value = {"null"})
})
public void shouldBeParameterizedWithNull(String value) {
    Assertions.assertNull(value);
}
```

For tests that need to pass `"null"` string to test invocation you can rename what value is replaced with `null` using `nullValue` attribute of `@DataTableSource`.

```java
@ParameterizedTest
@DataTableSource(value = {
    @Row({"null", "false"}),
    @Row({"nil", "true"}),
}, nullValue = "nil"
)
public void shouldAllowChangeNullValue(String value, Boolean isNull) {
    Assertions.assertEquals(isNull, value == null);
}
```