package fr.xebia.google.hashcode;

import fr.xebia.google.hashcode.file.FileUtils;
import fr.xebia.google.hashcode.model.DataCenter;

import java.sql.Timestamp;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class Launcher {

    public static void main(String[] args) {
//        String filePath = args[0];
        String filePath = "src/main/resources/dc.in";

        System.out.println(filePath);

        // Read file
        DataCenter dataCenter = FileUtils.readFileInDataCenter(filePath);

        System.out.println("data center loaded");

        // Write in result file
        Date date = new Date();
        String fileName = String.valueOf(date.getTime());
        FileUtils.writeServersInFile(dataCenter, "src/main/resources/" + fileName + ".txt");
    }

}
