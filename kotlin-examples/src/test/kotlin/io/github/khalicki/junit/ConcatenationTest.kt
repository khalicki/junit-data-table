package io.github.khalicki.junit

import io.github.khalicki.junit.datatable.DataTableSource
import io.github.khalicki.junit.datatable.Row
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest

class ConcatenationTest {

    @ParameterizedTest
    @DataTableSource(
        Row("a", "b", "ab"),
        Row("abc", "def", "abcdef"),
    )
    fun `should join strings`(first: String, second: String, result: String) {
        // expect
        assertEquals(result, first + second)
    }

    @ParameterizedTest
    @DataTableSource(
        Row("first", "second", "result", header = true),
        Row("1"    , "1"     , "2"    ),
        Row("2"    , "3"     , "5"    ),
    )
    fun `should sum integers`(first: Int, second: Int, result: Int) {
        assertEquals(result, first + second)
    }
}