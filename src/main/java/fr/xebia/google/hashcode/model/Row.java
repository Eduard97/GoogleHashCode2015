package fr.xebia.google.hashcode.model;

public class Row {
    private final int indice;
    private final int size;

    public Row(int indice, int size) {
        this.indice = indice;
        this.size = size;
    }

    public int getIndice() {
        return indice;
    }

    public int getSize() {
        return size;
    }
}
