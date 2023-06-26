package io.github.khalicki.junit.datatable;

import org.junit.jupiter.params.provider.ArgumentsSource;
import java.lang.annotation.*;

/**
 * {@code @DataTableSource} is an {@link ArgumentsSource} which provides access to
 * an array of test cases for parameterized test.
 *
 * <p>This is an equivalent of a data table from Spock framework.
 * Each {@code @Row} is a single test case with one or many test case arguments
 * </p>
 *
 * @since 0.1.0
 * @author Kamil Halicki
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ArgumentsSource(DataTableProvider.class)
public @interface DataTableSource {

    /**
     * Rows of test's data table.
     *
     * <p>Each Row from this array is a single test execution of parameterized test.</p>
     *
     * @since 0.1.0
     */
    Row[] value() default {};

    /**
     * Allows to override default string value that is treated as null value in test arguments.
     *
     * <p>Java doesn't allow passing nulls in annotations so every test argument from {@code @Row}
     * annotation equal to {@code nullValue} string (e.g. {@code "null"}) is replaced
     * with null value</p>
     *
     * @since 0.1.0
     */
    String nullValue() default "null";
}
