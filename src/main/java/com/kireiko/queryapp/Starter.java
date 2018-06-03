package com.kireiko.queryapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Starter {
    public static void main(String[] args) {
        Properties fileProperties = System.getProperties();

        String filePropertiesPath = "db.properties";
        if (fileProperties.containsKey("properties.location")) {
            filePropertiesPath = fileProperties.getProperty("properties.location");
        }

        Properties properties = new Properties();
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(filePropertiesPath);) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        QueryApp queryApp = new QueryApp(properties,"");//with // in the end
        queryApp.run();
    }
}
