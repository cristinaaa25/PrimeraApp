package com.example.primeraapp;

import java.util.Random;

public class Task {
    private int id;
    private String task;

    public Task(String n) {
        this.id = new Random().nextInt(999999);
        task = n;
    }

    public int getId() {
        return id;
    }
    public String getTask() {
        return task;
    }
}
