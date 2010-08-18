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
// TODO: can't just extend ChainTimer?
public class ChainTimerTask extends ChainedTask implements ITask {

    private ChainedTask task;
    private TimerManager manager;
    private int runningTask;

    public ChainTimerTask(ChainedTask task, TimerManager manager) {
        super(task.getId(), task.getName(), task.getTasks());
        this.task = task;
        this.manager = manager;
    }

    @Override
    public void run() {
        task.getTasks()[runningTask].run();
        runningTask++;

        if (runningTask < task.getTasks().length) {
            manager.startTimer(this);
        } else {
            manager.stopTimer(task);
        }
    }

    @Override
    public int getId() {
        return task.getId();
    }

    @Override
    public String getName() {
        // TODO: StringBuilder?
        String outcome = task.getName();

        if (manager.isTaskRunning(this)) {
            outcome += " - " + task.getTasks()[runningTask].getName();
        }
        
        return outcome;
    }

    @Override
    public long getInterval() {
        long outcome = 0;

        if (manager.isTaskRunning(task)) {
            outcome = manager.getTimeRemaining(task);
        } else {
            outcome = task.getInterval();
        }

        return outcome;
    }

    public ITask getCurrent() {
        return task.getTasks()[runningTask];
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
