package fr.xebia.google.hashcode.processor;

import fr.xebia.google.hashcode.model.DataCenter;
import fr.xebia.google.hashcode.model.Server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BasicProcess {

    private DataCenter dataCenter;

    public BasicProcess() {
    }

    public BasicProcess(DataCenter dataCenter) {
        this.dataCenter = dataCenter;
    }

    public void process() {
        // Tri des serveurs par tailles
        // On dépile et on les fait rentrer dans row disponible
        // On associe les groupes au serveur
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

}
