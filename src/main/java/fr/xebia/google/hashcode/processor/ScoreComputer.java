package fr.xebia.google.hashcode.processor;

import fr.xebia.google.hashcode.model.DataCenter;
import fr.xebia.google.hashcode.model.Server;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ScoreComputer {

    private final DataCenter dataCenter;

    private Integer[][] gridScore;

    ScoreComputer(DataCenter dataCenter) {
        this.dataCenter = dataCenter;
        this.gridScore = new Integer[dataCenter.groupCount][dataCenter.getRows().size()];

        for (int i = 0; i < gridScore.length; i++) {
            for (int j = 0; j < gridScore[i].length; j++) {
                gridScore[i][j] = 0;
            }
        }
    }

    public static Integer compute(DataCenter dataCenter) {
        ScoreComputer scoreComputer = new ScoreComputer(dataCenter);

        scoreComputer.initGrid();
        Map<Integer, Integer> minCapacityByGroup = scoreComputer.computeMinCapacityByGroup();

        return minCapacityByGroup.values().stream().min(Comparator.<Integer>naturalOrder()).get();
    }

    private void initGrid() {
        // Compter pour chaque groupe, sa capacité selon la row
        for (int i = 0; i < dataCenter.groupCount; i++) {
            final int finalI = i;

            dataCenter.getServers().stream()
                    .filter(s -> s.group != null)
                    .forEach(new Consumer<Server>() {
                        @Override
                        public void accept(Server server) {
                            if (server.getGroup() == finalI) {
                                gridScore[finalI][server.row] += server.getCapacity();
                            }                        }
                    });
        }
    }

    private Map<Integer, Integer> computeMinCapacityByGroup() {
        Map<Integer, Integer> maxCapacityByGroup = new HashMap<>();
        Map<Integer, Integer> sumCapacityByGroup = new HashMap<>();

        for (int group = 0; group < dataCenter.groupCount; group++) {
            for (int indiceRow = 0; indiceRow < dataCenter.getRows().size(); indiceRow++) {
                sumCapacityByGroup.put(
                        group,
                        sumCapacityByGroup.getOrDefault(group, 0) + gridScore[group][indiceRow]);

                maxCapacityByGroup.put(
                        group,
                        Math.max(maxCapacityByGroup.getOrDefault(group, 0), gridScore[group][indiceRow]));
            }
        }

        for (int group = 0; group < dataCenter.groupCount; group++) {
            sumCapacityByGroup.put(
                    group,
                    sumCapacityByGroup.get(group) - maxCapacityByGroup.get(group)
            );
        }

        return sumCapacityByGroup;
    }

}
