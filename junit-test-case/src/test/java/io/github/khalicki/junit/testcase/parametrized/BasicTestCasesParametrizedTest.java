package io.github.khalicki.junit.testcase.parametrized;

import io.github.khalicki.junit.testcase.TestCase;
import io.github.khalicki.junit.testcase.TestCaseSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;

public class BasicTestCasesParametrizedTest {

    @ParameterizedTest
    @TestCaseSource({
        @TestCase({"a", "b", "ab"}),
        @TestCase({"d", "e", "de"})
    })
    public void shouldConcatStrings(String first, String second, String result) {
        // expect
        Assertions.assertEquals(result, first + second);
    }

    @ParameterizedTest
    @TestCaseSource({
        @TestCase({"1", "1", "2"}),
        @TestCase({"2", "3", "5"})
    })
    public void shouldSumInts(int first, int second, int result) {
        // expect
        Assertions.assertEquals(result, first + second);
    }
}
