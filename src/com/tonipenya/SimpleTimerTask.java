/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya;

import java.util.TimerTask;

/**
 *
 * @author tonipenya
 */
// TODO: create test for equals method
public class SimpleTimerTask extends TimerTask implements ITask {

    // TODO: remove this variable
    private ITask task;
    private ITimerManager manager;

    public SimpleTimerTask(ITask task, ITimerManager manager) {
        this.task = task;
        this.manager = manager;
    }

    @Override
    public void run() {
        task.run();
        manager.stopTimer(this);
    }

    public int getId() {
        return task.getId();
    }

    public String getName() {
        return task.getName();
    }

    public long getInterval() {
        return task.getInterval();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ITask)) {
            return false;
        }

        ITask other = (ITask) o;

        return getId() == other.getId();
    }
}
