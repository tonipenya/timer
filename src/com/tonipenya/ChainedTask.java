/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya;

/**
 *
 * @author tonipenya
 */
public class ChainedTask implements ITask {

    private int id;
    private String name;
    private ITask[] tasks;
    private Runnable command;

    public ChainedTask(int id, String name, ITask[] tasks) {
        this(id, name, tasks, null);
    }

    public ChainedTask(int id, String name, ITask[] tasks, Runnable command) {
        if (name == null) {
            throw new UnsupportedOperationException("Name cannot be null.");
        }

        if (tasks == null) {
            throw new UnsupportedOperationException("Tasks cannot be null");
        }

        this.id = id;
        this.name = name;
        this.tasks = tasks;
        this.command = command;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getInterval() {
        long totalInterval = 0;

        for (ITask task : tasks) {
            totalInterval += task.getInterval();
        }

        return totalInterval;
    }

    public ITask[] getTasks() {
        return tasks;
    }

    public Runnable getCommand() {
        return command;
    }

    public void setCommand(Runnable command) {
        this.command = command;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ITask)) {
            return false;
        }

        ITask other = (ITask) o;

        return id == other.getId();
    }
}
