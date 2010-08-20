/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya;

import java.util.Set;

/**
 *
 * @author tonipenya
 */
public interface ITimerManager {

    void startTimer(ICommand command, long delay);

    void stopTimer(ICommand command);

    boolean isRunning(ICommand command);

    // TODO: See if it's possible to get this from command
    /**
     * @throws UnsupportedOperationException
     */
    long getTimeRemaining(ICommand command);

    Set<ICommand> getRunning();
}
