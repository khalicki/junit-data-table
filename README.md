# JUnit5 Data Table
[![CI](https://github.com/khalicki/junit-data-table/actions/workflows/ci.yml/badge.svg)](https://github.com/khalicki/junit-data-table/actions/workflows/ci.yml)

The library for defining two-dimensional data table of arguments for parameterized tests in JUnit5. 

## Quick start

To quickly start using JUnit Data Table follow these steps:

### Add dependency

For projects using Gradle add following dependency:
```kotlin
testImplementation("io.github.khalicki:junit-data-table:0.2.0")
```

For parameterized tests JUnit requires `junit-jupiter-params` so if you don't have it already add also:
```kotlin
testImplementation("org.junit.jupiter:junit-jupiter-params:<version>")
```

### Use data table to parametrize test

Add a `@Where` annotation to a test with JUnit's `@ParameterizedTest` annotation. A simple test with two test cases written in Java looks like that:
```java
@ParameterizedTest
@Where({
    @Row({"a", "b", "ab"}),
    @Row({"d", "e", "de"})
})
public void shouldConcatStrings(String first, String second, String result) {
    assertEquals(result, first + second);
}
```

## User guide

JUnit 5 supports parameterized tests as described in [Parameterized Tests](https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests) in JUnit documentation.
Each test is declared using `@ParameterizedTest` annotation and at least one *Arguments Source*. This library adds a new `Arguments Source` that has the ability to provide multiple arguments for each test invocation.

### DataTableSource

In order to pass arguments to parameterized test, a `@Where` annotation should be added to test method. The name of this annotation comes from [Data tables](https://spockframework.org/spock/docs/2.3/data_driven_testing.html#data-tables) concept from Spock framework. 

The `value` attribute of the annotation contain array of arguments for each test invocation. Each invocation is represented by `@Row` annotation. There should be at least one row defined in data table.

#### Row

`Row` annotation represents single row in data table, and can be used only inside `@Where`. It is a single test invocation with arguments declared using `value` attribute. It's a default attribute so attribute name can be omitted.

```java
@Row({"hello", "world"})
```

To pass these values to test invocation, annotated method should have same number of arguments.
```java
public void someTest(String firstArgument, String secondArgument) {}
```

`Row` annotation only accepts arguments of type `java.lang.String`. If you need test arguments of different type you can use [JUnit Argument Conversion](https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-argument-conversion) which converts `String` values to declared arguments.

```java
@ParameterizedTest
@Where({
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
@Where({
    @Row(value = {"null"})
})
public void shouldBeParameterizedWithNull(String value) {
    Assertions.assertNull(value);
}
```

For tests that need to pass `"null"` string to test invocation you can rename what value is replaced with `null` using `nullValue` attribute of `@Where`.

```java
@ParameterizedTest
@Where(value = {
    @Row({"null", "false"}),
    @Row({"nil", "true"}),
}, nullValue = "nil"
)
public void shouldAllowChangeNullValue(String value, Boolean isNull) {
    Assertions.assertEquals(isNull, value == null);
}
```

##### Header row

There is a possibility to add a row with data table column names. This is only added for readability just to have argument name closer to argument values. For header row set attribute `header` to `true`.

```java
@ParameterizedTest
@Where({
    @Row(value = {"first", "second", "result"}, header = true),
    @Row(value = {"1", "1", "2"}),
    @Row(value = {"2", "3", "5"})
})
public void shouldSumIntsAndIgnoreHeader(int first, int second, int result) {
    Assertions.assertEquals(result, first + second);
}
```

You can format data table in similar way the Spock framework to look like a table.

```java
@ParameterizedTest
@Where({
    @Row(value = {"first", "second", "result"}, header = true),
    @Row(value = {"1"    , "1"     , "2"     }),
    @Row(value = {"2"    , "3"     , "5"     })
})
public void shouldSumIntsAlignedColumns(int first, int second, int result) {
    Assertions.assertEquals(result, first + second);
}
```

It's also worth noting that in Kotlin language you don't need to write `value` attribute name when other attributes are defined so this test is less verbose.

```kotlin
@ParameterizedTest
@Where(
    Row("first", "second", "result", header = true),
    Row("1"    , "1"     , "2"    ),
    Row("2"    , "3"     , "5"    ),
)
fun `should sum integers`(first: Int, second: Int, result: Int) {
    assertEquals(result, first + second)
}
```

## Inspiration

This library is an effort to bring very powerful [Spock framework Data Tables](https://spockframework.org/spock/docs/2.3/data_driven_testing.html#data-tables) to tests written in Java and Kotlin.
Spock uses AST modifications, so it's not possible to make it looks the same in Java. However, it allows to define the same data table in a little more verbose way, but using same language that you use for your implementation (if Groovy is not your language of choice). Having the same programming language for tests and implementation makes it easier to work with Test Driven Development. 

