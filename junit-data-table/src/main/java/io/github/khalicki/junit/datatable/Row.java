package io.github.khalicki.junit.datatable;

/**
 * Annotation for a single row in a test's data table.
 * One row is also one test case in a parameterized test. It can have one or more test parameters
 * passed by value argument.
 *
 * @since 0.1.0
 * @author Kamil Halicki
 */
public @interface Row {

    /**
     * Test parameters for a given test case.
     * Only String values are accepted but JUnit can convert them to declared test arguments.
     *
     * @return Array of String parameters for a single test case
     */
    String[] value() default {};

    /**
     * Determines if a row should be treated as a table header. Such row is omitted when parameterized
     * test is executed. The purpose of such row is to add description for data table parameters.
     *
     * @return Pass true when the row has column names instead of test case parameters
     */
    boolean header() default false;
}
