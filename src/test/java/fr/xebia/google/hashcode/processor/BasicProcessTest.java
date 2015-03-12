package fr.xebia.google.hashcode.processor;

import com.google.common.collect.Lists;
import fr.xebia.google.hashcode.model.DataCenter;
import fr.xebia.google.hashcode.model.Row;
import fr.xebia.google.hashcode.model.Server;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicProcessTest {

    @Test
    public void should_sort_server_by_size() {
        // Given
        BasicProcess basicProcess = new BasicProcess(null);

        Server s1 = new Server(0, 0, 10);
        Server s2 = new Server(0, 0, 20);
        Server s3 = new Server(0, 0, 15);

        List<Server> servers = Lists.newArrayList(s1, s2, s3);

        // When
        List<Server> sortedServer = basicProcess.sortServerBySize(servers);

        // Then
        assertThat(sortedServer).containsExactly(s2, s3, s1);
    }

    @Test
    public void should_find_first_available_space() {
        // Given
        DataCenter dataCenter = new DataCenter();
        dataCenter.addRow(new Row(0, 5));
        dataCenter.addRow(new Row(1, 5));

        BasicProcess basicProcess = new BasicProcess(dataCenter);

        // When
        BasicProcess.Indices indices = basicProcess.findFirstLocationAvailable(3);

        // Then
        assertThat(indices.indiceRow).isEqualTo(0);
        assertThat(indices.indiceLocation).isEqualTo(0);

        // When
        dataCenter.allocateServer(new Server(0, 0, 3), 0, 0);

        indices = basicProcess.findFirstLocationAvailable(3);

        // Then
        assertThat(indices.indiceRow).isEqualTo(1);
        assertThat(indices.indiceLocation).isEqualTo(0);

        // When
        dataCenter.allocateServer(new Server(1, 0, 3), 0, 0);

        indices = basicProcess.findFirstLocationAvailable(2);

        // Then
        assertThat(indices.indiceRow).isEqualTo(0);
        assertThat(indices.indiceLocation).isEqualTo(3);
    }

}