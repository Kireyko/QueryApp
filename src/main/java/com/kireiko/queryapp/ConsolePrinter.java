package com.kireiko.queryapp;

import java.util.List;

public class ConsolePrinter {

    public void print(List<String> headersList, List<List<String>> rowsList) {
        TableGenerator tableGenerator = new TableGenerator();
        String output = tableGenerator.generateTable(headersList, rowsList);
        System.out.println(output);
    }
}
