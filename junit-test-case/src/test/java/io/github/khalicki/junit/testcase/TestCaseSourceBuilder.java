package io.github.khalicki.junit.testcase;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

public class TestCaseSourceBuilder {
    private final ArrayList<TestCase> testCases;

    public TestCaseSourceBuilder() {
        this.testCases = new ArrayList<>();
    }

    static TestCaseSourceBuilder testCaseSource() {
        return new TestCaseSourceBuilder();
    }

    public TestCaseSourceBuilder withTestCase(TestCaseBuilder testCaseBuilder) {
        testCases.add(testCaseBuilder.build());
        return this;
    }

    public TestCaseSource build() {
        return new TestCaseSource() {
            @Override
            public TestCase[] value() {
                return testCases.toArray(TestCase[]::new);
            }

            @Override
            public String nullValue() {
                return "null";
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return TestCaseSource.class;
            }
        };
    }
}
