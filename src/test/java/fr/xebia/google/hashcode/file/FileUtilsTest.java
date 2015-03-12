package fr.xebia.google.hashcode.file;

import fr.xebia.google.hashcode.model.DataCenter;
import org.junit.Test;

public class FileUtilsTest {

    @Test
    public void should_read_file_in_grid() throws Exception {
        // Given
        String fileName = "fileToRead.txt";
        String filePath = "src/test/resources";

        // When
        DataCenter dataCenter = FileUtils.readFileInDataCenter(filePath, fileName);

        // Then
        assertThat(dataCenter.()).isEqualTo(5);
        assertThat(grid.getColumnSize()).isEqualTo(7);
//
//        assertThat(grid.getCell(0, 0).getColorTarget()).isEqualTo(BLANK);
//        assertThat(grid.getCell(0, 1).getColorTarget()).isEqualTo(BLANK);
//        assertThat(grid.getCell(0, 2).getColorTarget()).isEqualTo(BLANK);
//        assertThat(grid.getCell(0, 3).getColorTarget()).isEqualTo(BLANK);
//        assertThat(grid.getCell(0, 4).getColorTarget()).isEqualTo(COLORED);
//
//        assertThat(grid.getCell(2, 2).getColorTarget()).isEqualTo(COLORED);
//
//        assertThat(grid.getCell(4, 6).getColorTarget()).isEqualTo(BLANK);
    }


}