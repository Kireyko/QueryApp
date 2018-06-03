package com.kireiko.queryapp;

import com.kireiko.queryapp.entity.Data;
import com.kireiko.queryapp.html.HtmlGenerator;
import com.kireiko.queryapp.io.FileSaver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class QueryApp {

    private String pathToFolder;
    private Properties properties;
    private ConsolePrinter consolePrinter;
    private HtmlGenerator htmlGenerator;
    private FileSaver fileSaver;

    public QueryApp(Properties properties, String pathToFolder) {
        this.properties = properties;
        this.pathToFolder = pathToFolder;
    }


    public Connection openDatabaseConnection() {
        try {
            Class.forName(properties.getProperty("jdbc.driverClassName"));
            Connection connection = DriverManager.getConnection(
                    properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.username"),
                    properties.getProperty("jdbc.password"));
            return connection;
        } catch (Exception e) {
            System.out.println(e.getClass().getCanonicalName());
            throw new RuntimeException(e);
        }
    }



    public void run() {
        try (Connection connection = openDatabaseConnection();
             Scanner scanner = new Scanner(System.in);) {

            String query;
            while (!"exit".equals((query = scanner.nextLine()))) {
                QueryExecutor queryExecutor = new QueryExecutor();
                Data data = queryExecutor.identifyAndExecute(connection, query);

                if(data!=null) {
                    consolePrinter = new ConsolePrinter();
                    consolePrinter.print(data.getHeadersList(), data.getRowsList());

                    htmlGenerator = new HtmlGenerator();
                    String reportContent = htmlGenerator.generateHtml(data.getHeadersList(), data.getRowsList());

                    fileSaver = new FileSaver();
                    fileSaver.save(pathToFolder + "newFile.html", reportContent);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
