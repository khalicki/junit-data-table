package io.github.khalicki.junit.datatable;

import org.junit.jupiter.params.provider.ArgumentsSource;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ArgumentsSource(DataTableProvider.class)
public @interface DataTableSource {
    Row[] value() default {};
    String nullValue() default "null";
}
