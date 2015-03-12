package fr.xebia.google.hashcode.model;

import org.junit.Test;

import static fr.xebia.google.hashcode.model.State.AVAILABLE;
import static fr.xebia.google.hashcode.model.State.NOT_AVAILABLE;
import static org.assertj.core.api.Assertions.assertThat;

public class DataCenterTest {

    @Test
    public void should_allocate_server() {
        // Given
        DataCenter dataCenter = new DataCenter();
        dataCenter.addRow(new Row(0, 5));
        dataCenter.addRow(new Row(1, 5));

        Server server = new Server(0, 0, 2);

        // When / Then
        for (Row row : dataCenter.getRows()) {
            for (int i = 0; i < row.locations.length; i++) {
                assertThat(row.locations[i]).isEqualTo(AVAILABLE);
            }
        }

        // When
        dataCenter.allocateServer(server, 1, 2);

        // Then
        assertThat(dataCenter.getLocation(1, 2)).isEqualTo(NOT_AVAILABLE);
    }

}