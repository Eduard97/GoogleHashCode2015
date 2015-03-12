package fr.xebia.google.hashcode.model;

import java.util.ArrayList;
import java.util.List;

public class DataCenter {

    private List<Row> rows = new ArrayList<>();

    private List<Server> servers = new ArrayList<>();

    public List<Row> getRows() {
        return rows;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void addRow(Row row) {
        this.rows.add(row);
    }

    public void addServer(Server server) {
        this.servers.add(server);
    }

    public void addServers(List<Server> servers) {
        this.servers = servers;
    }

    public void setUnavailableAt(int rowIndex, int location) {
        Row row = rows.get(rowIndex);
        row.locations[location] = State.NOT_AVAILABLE;
    }

    public int getUnavailableCount() {
        int unavailable = 0;

        for (Row r : rows) {
            for (State s : r.locations) {
                if (s == State.NOT_AVAILABLE) {
                    unavailable++;
                }
            }
        }

        return unavailable;
    }


    public void allocateServer(Server server, Integer indiceRow, Integer indiceLocation) {

    }
}
