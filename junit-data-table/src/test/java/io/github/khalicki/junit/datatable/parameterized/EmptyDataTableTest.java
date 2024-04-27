package io.github.khalicki.junit.datatable.parameterized;

import io.github.khalicki.junit.datatable.Where;
import io.github.khalicki.junit.datatable.Row;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.fail;

public class EmptyDataTableTest {

    @Disabled("JUnit throws PreconditionViolationException exception")
    @ParameterizedTest
    @ValueSource(ints = {})
    public void emptyValueSourceShouldThrowException() {
        fail("should not be called");
    }

    @Disabled("JUnit throws PreconditionViolationException exception")
    @ParameterizedTest
    @Where({})
    public void emptyDataTableShouldThrowException() {
        fail("should not be called");
    }

    @Disabled("JUnit throws PreconditionViolationException exception")
    @ParameterizedTest
    @Where({
        @Row(value = "testArgument", header = true)
    })
    public void dataTableWithOnlyHeaderShouldThrowException() {
        fail("should not be called");
    }
}
