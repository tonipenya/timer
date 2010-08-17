/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya;

import java.util.Timer;

/**
 *
 * @author tonipenya
 */
// TODO: create test for equals method
// TODO: can't just extend ChainTimer?
public class ChainTimerTask implements ITask {

    private ChainedTask task;
    private TimerManager manager;
    private int runningTask;

    public ChainTimerTask(ChainedTask task, TimerManager manager) {
        this.task = task;
        this.manager = manager;
    }

    @Override
    public void run() {
        task.getTasks()[runningTask].run();
        runningTask++;
        // TODO: Rewrite thise using scheduledThreadPool
//            Timer timer = new Timer(task.getName(), false);
//            timer.schedule(this, task.getInterval());
    }

    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public int getId() {
        return task.getId();
    }

    public String getName() {
        // TODO: StringBuilder?
        return task.getName() + " - " + task.getTasks()[runningTask].getName();
    }

    public long getInterval() {
        long outcome = 0;
        
        if(manager.isTaskRunning(task)) {
            outcome = manager.getTimeRemaining(task);
        } else {
            outcome = task.getInterval();
        }
        
        return outcome;
    }

    @Override
    public boolean equals(Object o) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
