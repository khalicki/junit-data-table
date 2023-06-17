package io.github.khalicki.junit.datatable;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * ArgumentsProvider that reads test cases from {@code @DataTableSource} annotation
 * and provides arguments for the execution of a parametrized test.
 *
 * @since 0.1.0
 * @author Kamil Halicki
 */
public class DataTableProvider implements ArgumentsProvider, AnnotationConsumer<DataTableSource> {

    private DataTableSource annotation;

    @Override
    public void accept(DataTableSource dataTableSource) {
        this.annotation = dataTableSource;
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
