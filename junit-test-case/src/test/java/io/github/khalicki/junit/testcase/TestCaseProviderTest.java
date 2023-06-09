package io.github.khalicki.junit.testcase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

import static io.github.khalicki.junit.testcase.TestCaseSourceBuilder.testCaseSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCaseProviderTest {

    private TestCaseProvider testCaseProvider;

    @BeforeEach
    void init() {
        testCaseProvider = new TestCaseProvider();
    }

    @Test
    void shouldCreateNewInstance() {
        // when
        new TestCaseProvider();
    }

    @Test
    void shouldAcceptEmptyArguments() {
        // given
        TestCaseSource annotation = testCaseSource().build();
        testCaseProvider.accept(annotation);

        // when
        Stream<Arguments> stream = testCaseProvider.provideArguments(null);

        // then
        assertEquals(stream.count(), 0);
    }

    @Test
    void shouldSingleTestCaseArgument() {
        // given
        TestCaseSource annotation = testCaseSource()
                .withTestCase(TestCaseBuilder.of("value1"))
                .build();
        testCaseProvider.accept(annotation);

        // when
        Stream<Arguments> stream = testCaseProvider.provideArguments(null);

        // then
        assertEquals(1, stream.count());
    }
}
