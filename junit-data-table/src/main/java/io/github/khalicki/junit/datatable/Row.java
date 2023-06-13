package io.github.khalicki.junit.datatable;

public @interface Row {
    String[] value() default {};
    boolean header() default false;
}
