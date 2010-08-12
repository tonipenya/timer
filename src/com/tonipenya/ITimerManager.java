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

    long getTimeRemaining(ITask task);
}
