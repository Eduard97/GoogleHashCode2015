package fr.xebia.google.hashcode.model;

public class Server {
    private final Integer indice;
    private final Integer capacity;
    private final Integer size;

    public Integer group;
    public Integer row;
    public Integer column;

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

    public Integer getSize() {
        return size;
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Server{" +
                "indice=" + indice +
                '}';
    }

}
