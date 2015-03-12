package fr.xebia.google.hashcode.model;

public class Server {
    private final int indice;
    private final int capacity;
    private final int size;

    public int group;
    public int row;
    public int column;

    public Server(int indice, int capacity, int size) {
        this.indice = indice;
        this.capacity = capacity;
        this.size = size;
    }

    public int getIndice() {
        return indice;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public int getGroup() {
        return group;
    }
}
