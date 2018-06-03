package com.kireiko.queryapp.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {
    public void save(String fileName, String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, false))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            //e.printStackTrace();
            throw new RuntimeException("issue during save to file: " + fileName, e);
        }
    }
}
