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

        Server server1 = new Server(0, 5, 1);
        server1.row = 3;
        server1.column = 3;
        server1.group = 0;
        Server server2 = new Server(1, 6, 1);
        server2.row = 5;
        server2.column = 9;
        server2.group = 2;
        Server server3 = new Server(2, 7, 1);
        server3.row = 1;
        server3.column = 5;
        server3.group = 1;

        dataCenter.addServer(server1);
        dataCenter.addServer(server2);
        dataCenter.addServer(server3);

        // When
        FileUtils.writeServersInFile(dataCenter, "src/test/resources/result.txt");
        List<String> result = FileUtils.readFileInString("src/test/resources/result.txt");

        // Then
        assertThat(result).hasSize(3);
        assertThat(result.get(0)).isEqualTo("3 3 0");
        assertThat(result.get(1)).isEqualTo("5 9 2");
        assertThat(result.get(2)).isEqualTo("1 5 1");
    }

}