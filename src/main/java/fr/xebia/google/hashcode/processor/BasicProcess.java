package fr.xebia.google.hashcode.processor;

import fr.xebia.google.hashcode.model.DataCenter;
import fr.xebia.google.hashcode.model.Server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BasicProcess implements Processor {

    private DataCenter dataCenter;

    public BasicProcess() {
    }

    public BasicProcess(DataCenter dataCenter) {
        this.dataCenter = dataCenter;
    }

    @Override
    public void process() {
        // Tri des serveurs par tailles
        List<Server> sortedServers = sortServerBySize(dataCenter.getServers());

        // On dépile et on les fait rentrer dans row disponible
        for (Server server : sortedServers) {
            Indices indices = findFirstLocationAvailable(server.getSize());

            dataCenter.allocateServer(server, indices.indiceRow, indices.indiceLocation);
        }

        // On associe les groupes au serveur
        associateGroup();
    }

    void associateGroup() {
    }

    Indices findFirstLocationAvailable(Integer size) {
        return null;
    }

    List<Server> sortServerBySize(List<Server> servers) {
        List<Server> sortedServers = new ArrayList<>(servers);

        sortedServers.sort(new Comparator<Server>() {
            @Override
            public int compare(Server s1, Server s2) {
                return -s1.getSize().compareTo(s2.getSize());
            }
        });

        return sortedServers;
    }

    class Indices {
        public Integer indiceRow;
        public Integer indiceLocation;
    }

}
