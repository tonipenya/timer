/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya;

import java.util.HashMap;
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
//    List<ITask> tasks;
    // TODO: This Map MUST die.
//    Map<Integer, ScheduledFuture> futuresMap;
    Map<ITask, ScheduledFuture> tasksMap;

    public TimerManager() {
//        tasks = new ArrayList<ITask>();
        stpe = new ScheduledThreadPoolExecutor(10);
//        futuresMap = new HashMap<Integer, ScheduledFuture>();
        tasksMap = new HashMap<ITask, ScheduledFuture>();
    }

    public void startTimer(ITask task) {
        ScheduledFuture future = stpe.schedule(task.getCommand(), task.getInterval(), TimeUnit.MILLISECONDS);
        tasksMap.put(task, future);

//        // TODO: A factory is going to be needed here!
//        long interval = 0;
//
//        ITask lTask;
//        if (task instanceof ChainTimerTask) {
//            lTask = task;
//            interval = ((ChainTimerTask) lTask).getCurrent().getInterval();
//        } else if (task instanceof ChainedTask) {
//            lTask = new ChainTimerTask((ChainedTask) task, this);
//            interval = ((ChainTimerTask) lTask).getCurrent().getInterval();
//        } else {
//            lTask = new SimpleTimerTask(task, this);
//            interval = task.getInterval();
//        }
//
//        ScheduledFuture future = stpe.schedule(lTask, interval, TimeUnit.MILLISECONDS);
//        futuresMap.put(task.getId(), future);
//
//        // TODO: No, this is not the way. Use a set or something that only allows one instance.
//        if (isTaskRunning(task)) {
//            tasks.set(tasks.lastIndexOf(task), task);
//        } else {
//            tasks.add(lTask);
//        }
    }

    public void stopTimer(ITask task) {
        if (isTaskRunning(task)) {
            tasksMap.get(task).cancel(false);
            tasksMap.remove(task);

//            ITask; lTask = tasks.get(tasks.lastIndexOf(task));
//            stpe.remove(lTask);
//            tasks.remove(lTask);
        }
    }

    public boolean isTaskRunning(ITask task) {
        return tasksMap.containsKey(task);
    }

    public <T extends ITask> ITask getRunningInstance(T task) {
        // TODO: Implement this method
            throw new UnsupportedOperationException("Not supported yet.");
//        ITask outcome = task;
//
//        if (isTaskRunning(task)) {
//            outcome = tasks.get(tasks.lastIndexOf(task));
//        }
//
//        return outcome;
    }

    public long getTimeRemaining(ITask task) {
        long remaining = 0;

        if (isTaskRunning(task)) {
            remaining = tasksMap.get(task).getDelay(TimeUnit.MILLISECONDS);
//            remaining = futuresMap.get(task.getId()).getDelay(TimeUnit.MILLISECONDS);
        }

        return remaining;
    }
}
