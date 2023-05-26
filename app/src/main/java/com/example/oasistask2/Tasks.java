package com.example.oasistask2;

public class Tasks {
    private int id;
    private String task;
    private boolean doneOrNot;

    public Tasks(String task, boolean doneOrNot) {
        this.task = task;
        this.doneOrNot = doneOrNot;
    }

    public Tasks(int id, String task, boolean doneOrNot) {
        this.id = id;
        this.task = task;
        this.doneOrNot = doneOrNot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isDoneOrNot() {
        return doneOrNot;
    }

    public void setDoneOrNot(boolean doneOrNot) {
        this.doneOrNot = doneOrNot;
    }
}
