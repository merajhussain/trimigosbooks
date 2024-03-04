package com.trimigos.models;

public class DataEntity {
    private String name;
    private int value;

    public DataEntity(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}