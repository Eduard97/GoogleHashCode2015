package fr.xebia.google.hashcode.processor;

import fr.xebia.google.hashcode.model.DataCenter;

public interface Processor {

    void process();

    DataCenter getDataCenter();

}
