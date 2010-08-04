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

    List<SimpleTimerTask> tasks;

    public TimerManager() {
        tasks = new ArrayList<SimpleTimerTask>();
    }

    public void startTimer(ITask task) {
        Timer timer = new Timer(task.getName(), false);
        SimpleTimerTask timerTask = new SimpleTimerTask(task, this);

        timer.schedule(timerTask, task.getInterval());
        tasks.add(timerTask);
    }

    public void stopTimer(ITask task) {
        if (tasks.contains(task)) {
            SimpleTimerTask stt = tasks.get(tasks.lastIndexOf(task));
            stt.cancel();
            tasks.remove(stt);
        }
    }

    public long getTimeRemaining(ITask task) {
        long remaining = 0;

        if (tasks.contains(task)) {
            SimpleTimerTask stt = tasks.get(tasks.lastIndexOf(task));
            remaining = stt.scheduledExecutionTime() - System.currentTimeMillis();
        }

        return remaining;
    }

    public List<ITask> getRunningTasks() {
        return new ArrayList<ITask>(tasks);
    }

    public ITask[] getAsArray() {
        ITask[] lTasks = new ITask[tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            lTasks[i] = tasks.get(i);
        }

        return lTasks;
    }

    public void run(ITask task) {
        System.out.println("Running task " + task.getName());
        tasks.remove(task);
    }
}
