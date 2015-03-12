package fr.xebia.google.hashcode.file;

import fr.xebia.google.hashcode.model.DataCenter;
import fr.xebia.google.hashcode.model.Server;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUtilsTest {

    @Test
    public void should_read_file_in_grid() throws Exception {
        // Given
        String filePath = "src/test/resources/fileToRead.txt";

        // When
        DataCenter dataCenter = FileUtils.readFileInDataCenter(filePath);

        // Then
        assertThat(dataCenter.getRows().size()).isEqualTo(2);
        assertThat(dataCenter.getRows().get(0).getSize()).isEqualTo(5);

//        assertThat(dataCenter.get().size()).isEqualTo(5);
        assertThat(dataCenter.getServers().size()).isEqualTo(5);
    }

    @Test
    public void should_read_unavailables() throws Exception {
        // Given
        String filePath = "src/test/resources/fileToRead.txt";

        // When
        DataCenter dataCenter = FileUtils.readFileInDataCenter(filePath);

        // Then
        assertThat(dataCenter.getUnavailableCount()).isEqualTo(1);
    }

    @Test
    public void should_write_simple_data_center_in_file() throws Exception {
        // Given
        DataCenter dataCenter = new DataCenter();
        dataCenter.addServer(new Server(0, 5, 1));
        dataCenter.addServer(new Server(1, 5, 1));
        dataCenter.addServer(new Server(2, 5, 1));

        // When
        FileUtils.writeServersInFile(dataCenter, "src/test/resources/result.txt");
        List<String> result = FileUtils.readFileInString("src/test/resources/result.txt");

        // Then
        assertThat(result).hasSize(3);
    }
    
}