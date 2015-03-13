package fr.xebia.google.hashcode.processor;

import com.google.common.collect.FluentIterable;
import fr.xebia.google.hashcode.model.DataCenter;
import fr.xebia.google.hashcode.model.Row;
import fr.xebia.google.hashcode.model.Server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BasicProcess implements Processor {

    public DataCenter dataCenter;

    public BasicProcess(DataCenter dataCenter) {
        this.dataCenter = dataCenter;
    }

    /**
     * Basic process: 201
     */
    @Override
    public void process() {
        // Tri des serveurs selon différentes stratégies
//        sortServerBySize(dataCenter.getServers()); // 201
//        sortServerByRatio(dataCenter.getServers()); // 201
//        Collections.shuffle(dataCenter.getServers());
        sortServerByCapacity(dataCenter.getServers()); // 201

        // On dépile et on les fait rentrer dans row disponible
        int startSearch = 0;
        boolean ascending = true;

        for (Server server : dataCenter.getServers()) {
            Indices indices = findFirstLocationAvailable(server.getSize(), startSearch);

            if (indices != null) {
                dataCenter.allocateServer(server, indices.indiceRow, indices.indiceLocation);
            }

            if (ascending) {
                startSearch++;
                if (startSearch >= dataCenter.getRows().size()) {
                    startSearch = dataCenter.getRows().size() - 1;
                    ascending = false;
                }
            }
            else {
                startSearch--;
                if (startSearch < 0) {
                    startSearch = 1;
                    ascending = true;
                }
            }
        }

        // On associe les groupes au serveur
        associateGroupWithSort();
    }

    private Server findServer(boolean searchMaxCapacity) {
        List<Server> servers = new ArrayList<>(FluentIterable.from(dataCenter.getServers()).filter(s -> !s.done).toList());

        if (servers.isEmpty()) {
            return null;
        }

        if (searchMaxCapacity) {
            sortServerByCapacityDesc(servers);
        }
        else {
            sortServerByCapacityAsc(servers);
        }

        Server result = servers.stream().findFirst().get();
        result.done = true;

        return result;
    }

    @Override
    public DataCenter getDataCenter() {
        return this.dataCenter;
    }

    void associateGroup() {
        Integer groupCount = dataCenter.groupCount;
        Integer currentGroup = 0;

        for (Row row : dataCenter.getRows()) {
            List<Server> servers = new ArrayList<>(dataCenter.findServerByIndiceRow(row.getIndice()));

            for (Server server : servers) {
                server.group = currentGroup;
                currentGroup = nextGroup(currentGroup, groupCount);
            }
        }
    }

    void associateGroupWithSort() {
        Integer groupCount = dataCenter.groupCount;
        Integer currentGroup = 0;

        List<Server> servers = new ArrayList<>();

        for (Row row : dataCenter.getRows()) {
            servers.addAll(dataCenter.findServerByIndiceRow(row.getIndice()));
        }

        sortServerByCapacity(servers);

        for (Server server : servers) {
            server.group = currentGroup;
            currentGroup = nextGroup(currentGroup, groupCount);
        }
    }

    Integer nextGroup(Integer currentGroup, Integer groupCount) {
        currentGroup++;

        if (currentGroup >= groupCount) {
            currentGroup = 0;
        }

        return currentGroup;
    }

    Indices findFirstLocationAvailable(Integer size) {
        for (Row row : dataCenter.getRows()) {
            Integer indiceLocation = row.findLocationFor(size);

            if (indiceLocation != null) {
                return new Indices(row.getIndice(), indiceLocation);
            }
        }

        return null;
    }

    Indices findFirstLocationAvailable(Integer size, int startSearchRow) {
        for (int i = startSearchRow; i < dataCenter.getRows().size(); i++) {
            Row row = dataCenter.findRow(i);

            Integer indiceLocation = row.findLocationFor(size);

            if (indiceLocation != null) {
                return new Indices(row.getIndice(), indiceLocation);
            }
        }

        for (Row row : dataCenter.getRows()) {
            Integer indiceLocation = row.findLocationFor(size);

            if (indiceLocation != null) {
                return new Indices(row.getIndice(), indiceLocation);
            }
        }

        return null;
    }

    Indices findFirstLocationAvailableSortedByRowIndices(Integer size, boolean isAscending) {
        List<Row> rows = dataCenter.getRows();

        if (isAscending) {
            rows.sort(new Comparator<Row>() {
                @Override
                public int compare(Row o1, Row o2) {
                    return o1.getIndice().compareTo(o2.getIndice());
                }
            });
        }
        else {
            rows.sort(new Comparator<Row>() {
                @Override
                public int compare(Row o1, Row o2) {
                    return o2.getIndice().compareTo(o1.getIndice());
                }
            });
        }

        for (Row row : rows) {
            Integer indiceLocation = row.findLocationFor(size);

            if (indiceLocation != null) {
                return new Indices(row.getIndice(), indiceLocation);
            }
        }

        return null;
    }

    void sortServerByCapacity(List<Server> servers) {
        servers.sort(new Comparator<Server>() {
            @Override
            public int compare(Server s1, Server s2) {
                return -s1.getCapacity().compareTo(s2.getCapacity());
            }
        });
    }

    void sortServerByCapacityDesc(List<Server> servers) {
        servers.sort(new Comparator<Server>() {
            @Override
            public int compare(Server s1, Server s2) {
                return -s1.getCapacity().compareTo(s2.getCapacity());
            }
        });
    }

    void sortServerByCapacityAsc(List<Server> servers) {
        servers.sort(new Comparator<Server>() {
            @Override
            public int compare(Server s1, Server s2) {
                return s1.getCapacity().compareTo(s2.getCapacity());
            }
        });
    }

    void sortServerByRatio(List<Server> servers) {
        servers.sort(new Comparator<Server>() {
            @Override
            public int compare(Server s1, Server s2) {
                Float ratio1 = Float.valueOf(s1.getSize() / s1.getCapacity());
                Float ratio2 = Float.valueOf(s2.getSize() / s2.getCapacity());

                return ratio1.compareTo(ratio2);
            }
        });
    }

    void sortServerBySize(List<Server> servers) {
        servers.sort(new Comparator<Server>() {
            @Override
            public int compare(Server s1, Server s2) {
                return -s1.getSize().compareTo(s2.getSize());
            }
        });
    }

    class Indices {
        public Integer indiceRow;
        public Integer indiceLocation;

        public Indices(Integer indiceRow, Integer indiceLocation) {
            this.indiceRow = indiceRow;
            this.indiceLocation = indiceLocation;
        }
    }

}
