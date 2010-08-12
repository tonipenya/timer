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

    List<AbstractTimerTask> tasks;

    public TimerManager() {
        tasks = new ArrayList<AbstractTimerTask>();
    }

    public void startTimer(ITask task) {
        Timer timer = new Timer(task.getName(), false);
        // TODO: A factory is going to be needed here!
        AbstractTimerTask timerTask = new SimpleTimerTask(task, this);

        timer.schedule(timerTask, task.getInterval());
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

    public long getTimeRemaining(ITask task) {
        long remaining = 0;

        if (isTaskRunning(task)) {
            AbstractTimerTask stt = tasks.get(tasks.lastIndexOf(task));
            remaining = stt.scheduledExecutionTime() - System.currentTimeMillis();
        }

        return remaining;
    }
}
