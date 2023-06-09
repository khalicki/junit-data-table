package io.github.khalicki.junit.datatable;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

public class DataTableSourceBuilder {
    private final ArrayList<Row> rows;

    public DataTableSourceBuilder() {
        this.rows = new ArrayList<>();
    }

    static DataTableSourceBuilder dataTableSource() {
        return new DataTableSourceBuilder();
    }

    public DataTableSourceBuilder withRow(RowBuilder rowBuilder) {
        rows.add(rowBuilder.build());
        return this;
    }

    public DataTableSource build() {
        return new DataTableSource() {
            @Override
            public Row[] value() {
                return rows.toArray(Row[]::new);
            }

            @Override
            public String nullValue() {
                return "null";
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return DataTableSource.class;
            }
        };
    }
}
