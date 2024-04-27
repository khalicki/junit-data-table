package io.github.khalicki.junit.datatable.parameterized;

import io.github.khalicki.junit.datatable.Where;
import io.github.khalicki.junit.datatable.Row;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

public class NullArgumentsTest {

    @ParameterizedTest
    @Where({
        @Row(value = {"null"})
    })
    public void shouldBeParameterizedWithNull(String value) {
        // expect
        Assertions.assertNull(value);
    }

    @ParameterizedTest
    @Where(value = {
        @Row({"null", "false"}),
        @Row({"nil", "true"}),
    }, nullValue = "nil"
    )
    public void shouldAllowChangeNullValue(String value, Boolean isNull) {
        // expect
        Assertions.assertEquals(isNull, value == null);
    }
}
