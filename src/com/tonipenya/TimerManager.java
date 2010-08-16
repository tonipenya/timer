/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 *
 * @author tonipenya
 */
public class TimerManager implements ITimerManager {
    // TODO: Remove task after being executed.
    List<AbstractTimerTask> tasks;

    public TimerManager() {
        tasks = new ArrayList<AbstractTimerTask>();
    }

    public void startTimer(ITask task) {
        // TODO: A factory is going to be needed here!
        long interval = 0;

        AbstractTimerTask timerTask;
        if (task instanceof ChainedTask) {
            timerTask = new ChainTimerTask((ChainedTask) task, this);
            // TODO: This is a potential ArrayIndexOutOfBounds
            interval = ((ChainedTask) task).getTasks()[0].getInterval();
        } else {
            timerTask = new SimpleTimerTask(task, this);
            interval = task.getInterval();
        }

        Timer timer = new Timer(task.getName(), false);
        timer.schedule(timerTask, interval);
        tasks.add(timerTask);
    }

    public void stopTimer(ITask task) {
        if (tasks.contains(task)) {
            AbstractTimerTask stt = tasks.get(tasks.lastIndexOf(task));
            stt.cancel();
            tasks.remove(stt);
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
            AbstractTimerTask stt = tasks.get(tasks.lastIndexOf(task));
            remaining = stt.scheduledExecutionTime() - System.currentTimeMillis();
        }

        return remaining;
    }
}
