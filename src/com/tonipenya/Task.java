/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya;

/**
 *
 * @author tonipenya
 */
public class Task implements ITask {

    private int id;
    private String name;
    private long interval;

    public Task(int id, String name, long interval) {
        this.id = id;
        this.name = name;
        this.interval = interval;
    }

    public int getId() {
        return id;
    }

    public long getInterval() {
        return interval;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ITask)) {
            return false;
        }

        ITask other = (ITask) o;

        return id == other.getId();
    }


    @Override
    public String toString() {
        // TODO: StringBuilder?
        return this.getClass().getName() + "[" + id + "]";
    }

    public void run() {
        System.out.println("Called run on " + this.getClass().getCanonicalName() +
                ". Think about overriding this method");
    }
}
