/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya;

import java.util.List;

/**
 *
 * @author tonipenya
 */
public interface ITimerManager {

    void startTimer(ITask task);

    void stopTimer(ITask task);

    boolean isTaskRunning(ITask task);

    List<ITask> getRunningTasks();

    long getTimeRemaining(ITask task);
}
