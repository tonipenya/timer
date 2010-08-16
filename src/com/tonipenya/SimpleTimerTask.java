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
public class SimpleTimerTask extends AbstractTimerTask {

    private ITask task;
    private ITimerManager manager;

    public SimpleTimerTask(ITask task, ITimerManager manager) {
        this.task = task;
        this.manager = manager;
    }

    @Override
    public void run() {
        task.execute();
        manager.stopTimer(this);
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
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
