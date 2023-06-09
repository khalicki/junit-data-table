package io.github.khalicki.junit.testcase;

public @interface TestCase {
    String[] value() default {};
    boolean header() default false;
}
