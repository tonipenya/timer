/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya;

/**
 *
 * @author tonipenya
 */
public interface ITimerManager {

    void startTimer(ITask task);

    void stopTimer(ITask task);

    boolean isTaskRunning(ITask task);

    // TODO: Rename and say clearly in the javadoc that, in case the task is not running, it returns the same received;
    <T extends ITask> ITask getRunningInstance(T task);

    long getTimeRemaining(ITask task);
}
