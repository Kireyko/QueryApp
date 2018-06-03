package com.kireiko.queryapp;

import com.kireiko.queryapp.entity.Data;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class QueryAppTest {
    private ConsolePrinter consolePrinter;
    String query1 = "select * from test.test_table;";
    String query2 = "select id, name from test.test_table;";
    String query3 = "select * from test.test_table where id<8;";
    String query4 = "select id, name from test.test_table where id<8;";

    String queryInsert = "insert into test.test_table(id, name) values( 3, 'new_name');";
    String queryInsert2 = "insert into test.test_table values( 3, 'new_name'); ";
    String queryUpdate = "update test.test_table set name='name3_updated', id=3;";
    String queryUpdate2 = "update test.test_table set name='name3_updated', id=3 where id=3;";

    String queryUpdateBad = "update test.test_table1 set name='name3_updated', id=3 where id=3;";

    String badQuery1 = "select id, name from test.test_table1 where id<8;";
    String badQuery2 = "MERGE id, name from test.test_table1 where id<8;";


    public Connection openConnection() {
        try {
            Properties properties = new Properties();
            try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("db.properties");) {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
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


    @Test
    public void runSelectGood() {
        Connection connection = openConnection();
        QueryExecutor queryExecutor = new QueryExecutor();

        Data data1 = queryExecutor.identifyAndExecute(connection, query1);
        if (data1 != null) {
            consolePrinter = new ConsolePrinter();
            consolePrinter.print(data1.getHeadersList(), data1.getRowsList());
        }
    }

    @Test
    public void runSelectBad() {
        Connection connection = openConnection();
        QueryExecutor queryExecutor = new QueryExecutor();
        Data data = queryExecutor.identifyAndExecute(connection, badQuery1);
        if (data != null) {
            consolePrinter = new ConsolePrinter();
            consolePrinter.print(data.getHeadersList(), data.getRowsList());
        }

    }
    @Test
    public void runBad() {
        Connection connection = openConnection();
        QueryExecutor queryExecutor = new QueryExecutor();
        Data data = queryExecutor.identifyAndExecute(connection, badQuery2);
        if (data != null) {
            consolePrinter = new ConsolePrinter();
            consolePrinter.print(data.getHeadersList(), data.getRowsList());
        }

    }


    @Test
    public void runInsertGood() {
        Connection connection = openConnection();
        QueryExecutor queryExecutor = new QueryExecutor();
        Data data = queryExecutor.identifyAndExecute(connection, queryInsert);
        if (data != null) {
            consolePrinter = new ConsolePrinter();
            consolePrinter.print(data.getHeadersList(), data.getRowsList());
        }

    }

    @Test
    public void runUpdateGood() {
        Connection connection = openConnection();
        QueryExecutor queryExecutor = new QueryExecutor();
        Data data = queryExecutor.identifyAndExecute(connection, queryUpdate);
        if (data != null) {
            consolePrinter = new ConsolePrinter();
            consolePrinter.print(data.getHeadersList(), data.getRowsList());
        }

    }
    @Test
    public void runUpdateBad() {
        Connection connection = openConnection();
        QueryExecutor queryExecutor = new QueryExecutor();
        Data data = queryExecutor.identifyAndExecute(connection, queryUpdateBad);
        if (data != null) {
            consolePrinter = new ConsolePrinter();
            consolePrinter.print(data.getHeadersList(), data.getRowsList());
        }

    }

}