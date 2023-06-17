package io.github.khalicki.junit.datatable.parametrized;

import io.github.khalicki.junit.datatable.DataTableSource;
import io.github.khalicki.junit.datatable.Row;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

public class NullArgumentsTest {

    @ParameterizedTest
    @DataTableSource({
        @Row(value = {"null"})
    })
    public void shouldBeParameterizedWithNull(String value) {
        // expect
        Assertions.assertNull(value);
    }

    @ParameterizedTest
    @DataTableSource(value = {
        @Row({"null", "false"}),
        @Row({"nil", "true"}),
    }, nullValue = "nil"
    )
    public void shouldAllowChangeNullValue(String value, Boolean isNull) {
        // expect
        Assertions.assertEquals(isNull, value == null);
    }
}
