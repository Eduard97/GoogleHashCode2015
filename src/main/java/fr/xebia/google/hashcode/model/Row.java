package fr.xebia.google.hashcode.model;

public class Row {
    private final int indice;
    private final int size;

    public State[] locations;

    public Row(int indice, int size) {
        this.indice = indice;
        this.size = size;
        this.locations = new State[size];
    }

    public int getIndice() {
        return indice;
    }

    public int getSize() {
        return size;
    }
}
