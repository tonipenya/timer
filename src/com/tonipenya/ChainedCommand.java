/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tonipenya;

import com.tonipenya.ChainedTask;

/**
 *
 * @author tonipenya
 */
public abstract class ChainedCommand implements ICommand{
    private ChainedTask task;
    private ITimerManager manager;
    private int next;

    public ChainedCommand(ChainedTask task, ITimerManager manager) {
        next = 0;
        this.task = task;
        this.manager = manager;
    }

    public String getName() {
        String name =task.getName();

        if(next < task.getTasks().length) {
            name += " - " + task.getTasks()[next].getName();
        }
        return name;
    }

    public void run() {
        implementation();
        next++;
        manager.startTimer(this, task.getTasks()[next].getInterval());
    }

    protected abstract void implementation();

}
