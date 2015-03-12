package fr.xebia.google.hashcode.file;

import fr.xebia.google.hashcode.model.DataCenter;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUtilsTest {

    @Test
    public void should_read_file_in_grid() throws Exception {
        // Given
        String fileName = "fileToRead.txt";
        String filePath = "src/test/resources";

        // When
        DataCenter dataCenter = FileUtils.readFileInDataCenter(filePath, fileName);

        // Then
        assertThat(dataCenter.getRows().size()).isEqualTo(2);
        assertThat(dataCenter.getRows().get(0).getSize()).isEqualTo(5);

//        assertThat(dataCenter.get().size()).isEqualTo(5);
        assertThat(dataCenter.getServers().size()).isEqualTo(5);
    }

    @Test
    public void should_read_unavailables() throws Exception {
        // Given
        String fileName = "fileToRead.txt";
        String filePath = "src/test/resources";

        // When
        DataCenter dataCenter = FileUtils.readFileInDataCenter(filePath, fileName);

        // Then
        assertThat(dataCenter.getUnavailableCount()).isEqualTo(1);
    }

}