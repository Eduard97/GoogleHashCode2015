package fr.xebia.google.hashcode;

import fr.xebia.google.hashcode.file.FileUtils;
import fr.xebia.google.hashcode.model.DataCenter;
import fr.xebia.google.hashcode.processor.BasicProcess;
import fr.xebia.google.hashcode.processor.Processor;
import fr.xebia.google.hashcode.processor.ScoreComputer;

import java.sql.Timestamp;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class Launcher {

    public static void main(String[] args) {
//        String filePath = args[0];
        String filePath = "src/main/resources/dc.in";
//        String filePath = "src/main/resources/fileToRead.txt";

        System.out.println(filePath);

        // Read file
        DataCenter dataCenter = FileUtils.readFileInDataCenter(filePath);
        System.out.println("data center loaded");

        Processor processor = new BasicProcess(dataCenter);
        processor.process();
        System.out.println("data center processed");

        System.out.println("The score for this processor is: " + ScoreComputer.compute(processor.getDataCenter()));

        // Write in result file
        Date date = new Date();
        String fileName = String.valueOf(date.getTime());
        FileUtils.writeServersInFile(dataCenter, "src/main/resources/" + fileName + ".txt");
    }

}
