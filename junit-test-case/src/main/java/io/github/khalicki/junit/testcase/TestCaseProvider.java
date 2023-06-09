package io.github.khalicki.junit.testcase;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.util.Arrays;
import java.util.stream.Stream;

public class TestCaseProvider implements ArgumentsProvider, AnnotationConsumer<TestCaseSource> {

    private TestCaseSource annotation;

    @Override
    public void accept(TestCaseSource testCaseSource) {
        this.annotation = testCaseSource;
    }

    @Override
    public Stream<Arguments> provideArguments(ExtensionContext context) {
        return Arrays.stream(this.annotation.value())
            .filter(it -> !it.header())
            .map(it -> Arguments.of(decodeNullValues(it.value())));
    }

    Object[] decodeNullValues(String[] strings) {
        return Arrays.stream(strings)
            .map(this::decodeNullValue)
            .toArray();
    }

    String decodeNullValue(String value) {
        if (value.equals(annotation.nullValue())) {
            return null;
        } else {
            return value;
        }
    }
}
