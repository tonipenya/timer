/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author tonipenya
 */
public class TimerManager implements ITimerManager {
    ScheduledThreadPoolExecutor stpe;
    List<ITask> tasks;
    // TODO: This Map MUST die.
    Map<Integer, ScheduledFuture> futuresMap;

    public TimerManager() {
        tasks = new ArrayList<ITask>();
        stpe = new ScheduledThreadPoolExecutor(10);
        futuresMap = new HashMap<Integer, ScheduledFuture>();
    }

    public void startTimer(ITask task) {
        // TODO: A factory is going to be needed here!
        long interval = 0;

        ITask lTask;
        if (task instanceof ChainedTask) {
            lTask = new ChainTimerTask((ChainedTask) task, this);
            // TODO: This is a potential ArrayIndexOutOfBounds
            interval = ((ChainedTask) task).getTasks()[0].getInterval();
        } else {
            lTask = new SimpleTimerTask(task, this);
            interval = task.getInterval();
        }

        ScheduledFuture future = stpe.schedule(lTask, interval, TimeUnit.MILLISECONDS);
        futuresMap.put(task.getId(), future);

        tasks.add(lTask);
    }

    public void stopTimer(ITask task) {
        if (isTaskRunning(task)) {
            ITask lTask = tasks.get(tasks.lastIndexOf(task));
            stpe.remove(lTask);
            tasks.remove(lTask);
        }
    }

    public boolean isTaskRunning(ITask task) {
        return tasks.contains(task);
    }

    public <T extends ITask> ITask getRunningInstance(T task) {

        ITask outcome = task;
        if (isTaskRunning(task)) {
            outcome = tasks.get(tasks.lastIndexOf(task));
        }

        return outcome;
    }

    public long getTimeRemaining(ITask task) {
        long remaining = 0;

        if (isTaskRunning(task)) {
            // TODO: this doesn't work
            remaining = futuresMap.get(task.getId()).getDelay(TimeUnit.MILLISECONDS);
        }

        return remaining;
    }
}
