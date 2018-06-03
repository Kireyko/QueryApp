package com.kireiko.queryapp.entity;


import java.util.List;

public class Data {
    private final List<String> headersList;
    private final List<List<String>> rowsList;

    public Data(List<String> headersList, List<List<String>> rowsList) {
        this.headersList = headersList;
        this.rowsList = rowsList;
    }

    public List<String> getHeadersList() {
        return headersList;
    }

    public List<List<String>> getRowsList() {
        return rowsList;
    }

    @Override
    public String toString() {
        return "Data{" +
                "headersList=" + headersList +
                ", rowsList=" + rowsList +
                '}';
    }
}
