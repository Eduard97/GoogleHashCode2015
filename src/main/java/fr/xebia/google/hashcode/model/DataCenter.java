package fr.xebia.google.hashcode.model;

import java.util.ArrayList;
import java.util.List;

public class DataCenter {

    private List<Row> rows = new ArrayList<>();

    public List<Row> getRows() {
        return rows;
    }

    public void addRow(Row row) {
        this.rows.add(row);
    }
}
