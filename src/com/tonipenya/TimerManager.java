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
    List<SimpleTimerTask> tasks;
    // TODO: This Map MUST die.
    Map<Integer, ScheduledFuture> futuresMap;

    public TimerManager() {
        tasks = new ArrayList<SimpleTimerTask>();
        stpe = new ScheduledThreadPoolExecutor(10);
        futuresMap = new HashMap<Integer, ScheduledFuture>();
    }

    public void startTimer(ITask task) {
        SimpleTimerTask timerTask = new SimpleTimerTask(task, this);

        ScheduledFuture future = stpe.schedule(timerTask, task.getInterval(), TimeUnit.MILLISECONDS);
        futuresMap.put(task.getId(), future);

        tasks.add(timerTask);
    }

    public void stopTimer(ITask task) {
        if (isTaskRunning(task)) {
            SimpleTimerTask stt = tasks.get(tasks.lastIndexOf(task));
            stpe.remove(stt);
            tasks.remove(stt);
        }
    }

    public boolean isTaskRunning(ITask task) {
        return tasks.contains(task);
    }

    public long getTimeRemaining(ITask task) {
        long remaining = 0;

        if (isTaskRunning(task)) {
            // TODO: this doesn't work
            remaining = futuresMap.get(task.getId()).getDelay(TimeUnit.MILLISECONDS);
        }

        return remaining;
    }

    public List<ITask> getRunningTasks() {
        return new ArrayList<ITask>(tasks);
    }
}
