/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya.timer.dmo;

/**
 *
 * @author tonipenya
 */
public class ChainedTask implements ITask {

    private int id;
    private String name;
    private ITask[] tasks;

    public ChainedTask(int id, String name, ITask[] tasks) {
        if (name == null) {
            throw new UnsupportedOperationException("Name cannot be null.");
        }

        if (tasks == null) {
            throw new UnsupportedOperationException("Tasks cannot be null");
        }

        this.id = id;
        this.name = name;
        this.tasks = tasks;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ITask)) {
            return false;
        }

        ITask other = (ITask) o;

        return id == other.getId();
    }
}
