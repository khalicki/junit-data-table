package io.github.khalicki.junit.testcase.parametrized;

import io.github.khalicki.junit.testcase.TestCase;
import io.github.khalicki.junit.testcase.TestCaseSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

public class NullArgumentsTest {

    @ParameterizedTest
    @TestCaseSource({
        @TestCase({"null"})
    })
    public void shouldBeParameterizedWithNull(String value) {
        // expect
        Assertions.assertNull(value);
    }

    @ParameterizedTest
    @TestCaseSource(value = {
        @TestCase({"null", "false"}),
        @TestCase({"nil", "true"}),
    }, nullValue = "nil"
    )
    public void shouldAllowChangeNullValue(String value, Boolean isNull) {
        // expect
        Assertions.assertEquals(isNull, value == null);
    }
}
