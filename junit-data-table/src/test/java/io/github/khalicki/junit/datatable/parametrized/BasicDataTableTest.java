package io.github.khalicki.junit.datatable.parametrized;

import io.github.khalicki.junit.datatable.DataTableSource;
import io.github.khalicki.junit.datatable.Row;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicDataTableTest {

    @ParameterizedTest
    @DataTableSource({
        @Row({"a", "b", "ab"}),
        @Row({"d", "e", "de"})
    })
    public void shouldConcatStrings(String first, String second, String result) {
        assertEquals(result, first + second);
    }

    @ParameterizedTest
    @DataTableSource({
        @Row({"1", "1", "2"}),
        @Row({"2", "3", "5"})
    })
    public void shouldSumInts(int first, int second, int result) {
        // expect
        assertEquals(result, first + second);
    }
}
