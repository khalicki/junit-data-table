package io.github.khalicki.junit.datatable;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.params.provider.ArgumentsSource;

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
public @interface Where {

    /**
     * Rows of test's data table.
     *
     * <p>Each Row from this array is a single test execution of parameterized test.</p>
     *
     * @return An array of test cases for the parametrized test
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
     * @return A String value that should be treated as null value
     * @since 0.1.0
     */
    String nullValue() default "null";
}
