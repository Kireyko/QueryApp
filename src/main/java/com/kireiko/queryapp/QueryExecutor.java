package com.kireiko.queryapp;

import com.kireiko.queryapp.entity.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryExecutor {

    public Data identifyAndExecute(Connection connection, String query) {
        Data data = null;
        try {
            if (query.toUpperCase().startsWith("SELECT")) {
                data = select(connection, query);
            } else {
                int rowsCount = execute(connection, query);
                System.out.println("done, for " + rowsCount + " rows");
            }
        } catch (SQLException e) {
            System.out.println(
                    "Request was failed with error code :" + e.getErrorCode() + System.lineSeparator()
                            + "error message: " + e.getMessage()
            );
        }
        return data;
    }

    int execute(Connection connection, String sqlText) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(sqlText);
        }
    }

    Data select(Connection connection, String query) throws SQLException {
        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(query);
            return (resultSet.isBeforeFirst()) ? resultSetToList(resultSet) : null;
        }
    }

    Data resultSetToList(ResultSet resultSet) throws SQLException {
        List<String> headersList = new ArrayList<>();
        List<List<String>> rowsList = new ArrayList<>();

        int columnCount = resultSet.getMetaData().getColumnCount();
        for (int n = 0; n < columnCount; n++) {
            headersList.add(resultSet.getMetaData().getColumnName(n));
        }

        while (resultSet.next()) {
            List<String> rowValues = new ArrayList<>();
            rowsList.add(rowValues);
            for (int i = 0; i < columnCount; i++) {
                rowValues.add(resultSet.getObject(i).toString());
            }
        }
        Data data = new Data(headersList, rowsList);
        return data;
    }

}
