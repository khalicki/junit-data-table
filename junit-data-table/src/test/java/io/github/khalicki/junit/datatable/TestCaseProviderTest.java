package io.github.khalicki.junit.datatable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static io.github.khalicki.junit.datatable.DataTableSourceBuilder.dataTableSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCaseProviderTest {

    private DataTableProvider dataTableProvider;

    @BeforeEach
    void init() {
        dataTableProvider = new DataTableProvider();
    }

    @Test
    void shouldCreateNewInstance() {
        // when
        new DataTableProvider();
    }

    @Test
    void shouldAcceptEmptyArguments() {
        // given
        DataTableSource annotation = dataTableSource().build();
        dataTableProvider.accept(annotation);

        // when
        Stream<Arguments> stream = dataTableProvider.provideArguments(null);

        // then
        assertEquals(stream.count(), 0);
    }

    @Test
    void shouldSingleTestCaseArgument() {
        // given
        DataTableSource annotation = dataTableSource()
                .withRow(RowBuilder.of("value1"))
                .build();
        dataTableProvider.accept(annotation);

        // when
        Stream<Arguments> stream = dataTableProvider.provideArguments(null);

        // then
        assertEquals(1, stream.count());
    }
}
