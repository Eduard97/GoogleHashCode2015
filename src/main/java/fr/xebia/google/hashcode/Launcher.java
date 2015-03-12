package fr.xebia.google.hashcode;

import fr.xebia.google.hashcode.file.FileUtils;
import fr.xebia.google.hashcode.model.DataCenter;

public class Launcher {

    public static void main(String[] args) {
        String filePath = args[0];

        System.out.println(filePath);

        DataCenter dataCenter = FileUtils.readFileInDataCenter(filePath);

        System.out.println("data center loaded");

    }

}
