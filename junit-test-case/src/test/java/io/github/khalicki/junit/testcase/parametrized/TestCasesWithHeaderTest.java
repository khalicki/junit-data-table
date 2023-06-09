package io.github.khalicki.junit.testcase.parametrized;

import io.github.khalicki.junit.testcase.TestCase;
import io.github.khalicki.junit.testcase.TestCaseSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

public class TestCasesWithHeaderTest {

    @ParameterizedTest
    @TestCaseSource({
        @TestCase(value = {"first", "second", "result"}, header = true),
        @TestCase(value = {"1"    , "1"     , "2"     }),
        @TestCase(value = {"2"    , "3"     , "5"     })
    })
    public void shouldSumIntsAndIgnoreHeader(int first, int second, int result) {
        // expect
        Assertions.assertEquals(result, first + second);
    }
}
