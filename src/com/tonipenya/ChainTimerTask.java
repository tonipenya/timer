/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya;

/**
 *
 * @author tonipenya
 */
// TODO: create test for equals method
public class ChainTimerTask extends AbstractTimerTask {

    private TaskChain task;
    private ITimerManager manager;

    public ChainTimerTask(TaskChain task, ITimerManager manager) {
        this.task = task;
        this.manager = manager;
    }

    @Override
    public void run() {
        task.run();
        manager.stopTimer(task);
        manager.startTimer(task);
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
