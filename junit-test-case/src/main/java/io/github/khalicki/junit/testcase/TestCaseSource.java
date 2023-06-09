package io.github.khalicki.junit.testcase;

import org.junit.jupiter.params.provider.ArgumentsSource;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ArgumentsSource(TestCaseProvider.class)
public @interface TestCaseSource {
    TestCase[] value() default {};
    String nullValue() default "null";
}
