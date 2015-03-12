package fr.xebia.google.hashcode.processor;

import fr.xebia.google.hashcode.model.DataCenter;
import fr.xebia.google.hashcode.model.Row;
import fr.xebia.google.hashcode.model.Server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BasicProcess implements Processor {

    private DataCenter dataCenter;

    public BasicProcess(DataCenter dataCenter) {
        this.dataCenter = dataCenter;
    }

    /**
     * Basic process: 201
     */
    @Override
    public void process() {
        // Tri des serveurs par tailles
//        sortServerBySize(dataCenter.getServers()); // 201
        sortServerByCapacity(dataCenter.getServers()); // 201
//        sortServerByRatio(dataCenter.getServers()); // 201

        // On dépile et on les fait rentrer dans row disponible
        for (Server server : dataCenter.getServers()) {
            Indices indices = findFirstLocationAvailable(server.getSize());

            if (indices != null) {
                dataCenter.allocateServer(server, indices.indiceRow, indices.indiceLocation);
            }
        }

        // On associe les groupes au serveur
        associateGroupWithSort();
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

    void sortServerByCapacity(List<Server> servers) {
        servers.sort(new Comparator<Server>() {
            @Override
            public int compare(Server s1, Server s2) {
                return -s1.getCapacity().compareTo(s2.getCapacity());
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
