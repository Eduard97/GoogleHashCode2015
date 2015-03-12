package fr.xebia.google.hashcode.model;

import org.junit.Test;

import static fr.xebia.google.hashcode.model.State.NOT_AVAILABLE;
import static org.assertj.core.api.Assertions.assertThat;

public class RowTest {

    @Test
    public void should_find_location_for_size() {
        // Given
        Row row = new Row(0, 10);
        row.locations[0] = NOT_AVAILABLE;

        // When
        Integer result = row.findLocationFor(3);

        // Then
        assertThat(result).isEqualTo(1);
    }

}