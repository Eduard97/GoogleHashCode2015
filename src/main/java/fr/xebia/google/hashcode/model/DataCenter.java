package fr.xebia.google.hashcode.model;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static fr.xebia.google.hashcode.model.State.NOT_AVAILABLE;

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
        row.locations[location] = NOT_AVAILABLE;
    }

    public int getUnavailableCount() {
        int unavailable = 0;

        for (Row r : rows) {
            for (State s : r.locations) {
                if (s == NOT_AVAILABLE) {
                    unavailable++;
                }
            }
        }

        return unavailable;
    }

    public void allocateServer(Server server, Integer indiceRow, Integer indiceLocation) {
        Row row = findRow(indiceRow);

        for (int i = indiceLocation; i < indiceLocation + server.getSize(); i++) {
            row.locations[i] = NOT_AVAILABLE;
        }
    }

    public State getLocation(int indiceRow, int indiceLocation) {
        Row row = findRow(indiceRow);

        return row.locations[indiceLocation];
    }

    public Row findRow(final int indiceRow) {
        return Iterables.find(rows, new Predicate<Row>() {
            @Override
            public boolean apply(Row row) {
                return row.getIndice() == indiceRow;
            }
        });
    }

    public List<Server> getUnusedServers() {
        List<Server> unusedServers = servers
                .stream()
                .filter(server -> server.column == null)
                .collect(Collectors.<Server>toList());

        Collections.sort(unusedServers, (o1, o2) -> o1.getIndice().compareTo(o2.getIndice()));

        return unusedServers;
    }

}
