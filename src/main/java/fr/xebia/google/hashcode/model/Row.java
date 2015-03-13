package fr.xebia.google.hashcode.model;

import static fr.xebia.google.hashcode.model.State.AVAILABLE;
import static fr.xebia.google.hashcode.model.State.NOT_AVAILABLE;

public class Row {
    private final Integer indice;
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

    public Integer getIndice() {
        return indice;
    }

    public int getSize() {
        return size;
    }

    public Integer findLocationFor(Integer size) {
        int actualSize = 0;
        int indiceLocation = 0;

        for (int i = 0; i < locations.length; i++) {
            if (locations[i] == NOT_AVAILABLE) {
                actualSize = 0;
                indiceLocation = i+1;
            }
            else {
                actualSize++;

                if (actualSize == size) {
                    return indiceLocation;
                }
            }
        }

        return null;
    }
}
