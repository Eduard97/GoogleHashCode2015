package fr.xebia.google.hashcode.model;

public class Server {
    private final Integer indice;
    private final Integer capacity;
    private final Integer size;

    public Integer group;
    public Integer row;
    public Integer column;
    public boolean done = false;

    public Server(int indice, int capacity, int size) {
        this.indice = indice;
        this.capacity = capacity;
        this.size = size;
    }

    public Integer getIndice() {
        return indice;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getSize() {
        return size;
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String toString() {
        if (column == null) {
            return "x";
        }

        return row + " " + column + " " + group;
    }

}
