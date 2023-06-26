package io.github.khalicki.junit.datatable.parametrized;

import io.github.khalicki.junit.datatable.Row;
import io.github.khalicki.junit.datatable.DataTableSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

public class DataTableWithHeaderTest {

    @ParameterizedTest
    @DataTableSource({
        @Row(value = {"first", "second", "result"}, header = true),
        @Row(value = {"1", "1", "2"}),
        @Row(value = {"2", "3", "5"})
    })
    public void shouldSumIntsAndIgnoreHeader(int first, int second, int result) {
        Assertions.assertEquals(result, first + second);
    }

    @ParameterizedTest
    @DataTableSource({
        @Row(value = {"first", "second", "result"}, header = true),
        @Row(value = {"1"    , "1"     , "2"     }),
        @Row(value = {"2"    , "3"     , "5"     })
    })
    public void shouldSumIntsAlignedColumns(int first, int second, int result) {
        Assertions.assertEquals(result, first + second);
    }
}
