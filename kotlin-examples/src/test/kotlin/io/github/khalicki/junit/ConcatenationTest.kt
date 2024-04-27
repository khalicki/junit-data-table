package io.github.khalicki.junit

import io.github.khalicki.junit.datatable.Row
import io.github.khalicki.junit.datatable.Where
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest

class ConcatenationTest {

    @ParameterizedTest
    @Where(
        Row("a", "b", "ab"),
        Row("d", "e", "de"),
    )
    fun `should join strings`(first: String, second: String, result: String) {
        assertEquals(result, first + second)
    }

    @ParameterizedTest
    @Where(
        // @formatter:off
        Row("first", "second","result", header = true),
        Row("1"    , "1"     , "2"    ),
        Row("2"    , "3"     , "5"    ),
        // @formatter:on
    )
    fun `should sum integers`(first: Int, second: Int, result: Int) {
        assertEquals(result, first + second)
    }

    @ParameterizedTest
    @Where(
        Row("foo", "bar", "foobar"),
    )
    fun `should join strings - one test case`(first: String, second: String, result: String) {
        assertEquals(result, first + second)
    }
}