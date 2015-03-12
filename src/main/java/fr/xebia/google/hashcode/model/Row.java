package fr.xebia.google.hashcode.model;

import static fr.xebia.google.hashcode.model.State.AVAILABLE;

public class Row {
    private final int indice;
    private final int size;

    public State[] locations;

    public Row(int indice, int size) {
        this.indice = indice;
        this.size = size;
        this.locations = new State[size];

        for (int i = 0; i < locations.length; i++) {
            locations[i] = AVAILABLE;
        }
    }

    public int getIndice() {
        return indice;
    }

    public int getSize() {
        return size;
    }
}
