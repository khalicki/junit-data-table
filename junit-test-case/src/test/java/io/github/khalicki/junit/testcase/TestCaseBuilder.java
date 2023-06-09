package io.github.khalicki.junit.testcase;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class TestCaseBuilder {
    private final List<String> arguments;

    public TestCaseBuilder(List<String> arguments) {
        this.arguments = arguments;
    }

    static TestCaseBuilder of(String... values) {
        TestCaseBuilder builder = new TestCaseBuilder(Arrays.stream(values).toList());
        return builder;
    }

    public TestCase build() {
        return new TestCase() {
            @Override
            public String[] value() {
                return arguments.toArray(String[]::new);
            }

            @Override
            public boolean header() {
                return false;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return TestCase.class;
            }
        };
    }
}
