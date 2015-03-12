package fr.xebia.google.hashcode.processor;

import fr.xebia.google.hashcode.model.DataCenter;
import fr.xebia.google.hashcode.model.Row;
import fr.xebia.google.hashcode.model.Server;

import java.util.*;

public class BasicProcess2 extends BasicProcess {

    public BasicProcess2(DataCenter dataCenter) {
        super(dataCenter);
    }

    @Override
    void associateGroupWithSort() {

        HashMap<Integer, Integer> groups = new HashMap<>();
        Integer groupCount = dataCenter.groupCount;

        for (int i = 0; i < groupCount; i++) {
            groups.put(i, 0);
        }

        Integer currentGroup = 0;

        List<Server> servers = new ArrayList<>();

        for (Row row : dataCenter.getRows()) {
            servers.addAll(dataCenter.findServerByIndiceRow(row.getIndice()));
        }

        sortServerByCapacity(servers);

        for (Server server : servers) {

            int minKey = 30000;

            server.group = currentGroup;
            groups.put(currentGroup, groups.get(currentGroup) + server.getCapacity());

            // Sort by capacity
            for (Map.Entry<Integer, Integer> entry : groups.entrySet()) {
                if (entry.getValue() < minKey) {
                    minKey = entry.getValue();
                    currentGroup = entry.getKey();
                }
            }
        }
    }
}
