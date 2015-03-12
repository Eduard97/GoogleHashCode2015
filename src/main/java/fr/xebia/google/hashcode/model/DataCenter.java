package fr.xebia.google.hashcode.model;

import java.util.ArrayList;
import java.util.List;

public class DataCenter {

    private List<Row> rows;

    public DataCenter(int nbRow) {
        rows = new ArrayList<>(nbRow);

        for (int i = 0; i < nbRow; i++) {
            rows.add(new Row(i));
        }
    }
}
