package fr.xebia.google.hashcode.file;

import fr.xebia.google.hashcode.model.DataCenter;
import javafx.util.Pair;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class FileUtils {

    public static DataCenter readFileInDataCenter(String filePath, String fileName) {
        DataCenter dataCenter;

        Path path = FileSystems.getDefault().getPath(filePath, fileName);
        String[] firstLine = readFirstLine(path);
        int notAvailableNumber = parseInt(firstLine[0]);

        dataCenter = new DataCenter(parseInt(firstLine[0]));

        List<String[]> notAvalableLines = readNotAvalableLines(path, notAvailableNumber);

        List<String[]> serverLines = readServerLines(path, 1 + notAvailableNumber);

        return dataCenter;
    }

    private static String[] readFirstLine(Path path) {
        try (Stream<String> fileLines = Files.lines(path)) {
            return fileLines.findFirst()
                    .map(s -> s.split(" "))
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<String[]> readNotAvalableLines(Path path, int n) {
        try (Stream<String> fileLines = Files.lines(path)) {
            return fileLines.skip(1).limit(n).map(s -> s.split(" ")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<String[]> readServerLines(Path path, int begin) {
        try (Stream<String> fileLines = Files.lines(path)) {
            return fileLines.skip(begin).map(s -> s.split(" ")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
