package io.github.khalicki.junit.datatable;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class RowBuilder {
    private final List<String> arguments;

    public RowBuilder(List<String> arguments) {
        this.arguments = arguments;
    }

    static RowBuilder of(String... values) {
        return new RowBuilder(Arrays.stream(values).toList());
    }

    public Row build() {
        return new Row() {
            @Override
            public String[] value() {
                return arguments.toArray(String[]::new);
            }

            @Override
            public boolean header() {
                return false;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return Row.class;
            }
        };
    }
}
