/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya.timer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author tonipenya
 */
public class TimerManager implements ITimerManager {

    private ScheduledThreadPoolExecutor stpe;
    private Map<ICommand, ScheduledFuture> commandMap;

    public TimerManager() {
        stpe = new ScheduledThreadPoolExecutor(10);
        commandMap = new HashMap<ICommand, ScheduledFuture>();
    }

    public void startTimer(ICommand command, long delay) {
        ScheduledFuture future = stpe.schedule(new CommandWrapper(command), delay, TimeUnit.MILLISECONDS);
        commandMap.put(command, future);
    }

    public void stopTimer(ICommand command) {
        commandMap.get(command).cancel(false);
        commandMap.remove(command);
    }

    public long getTimeRemaining(ICommand command) {
        ScheduledFuture future = commandMap.get(command);

        if (future == null) {
            throw new UnsupportedOperationException("The command is not registered");
        }

        return future.getDelay(TimeUnit.MILLISECONDS);
    }

    public Set<ICommand> getRunning() {
        return commandMap.keySet();
    }

    public boolean isRunning(ICommand command) {
        return commandMap.containsKey(command);
    }
    
    // TODO: This adds an extra class almost for free. Looks like a bad design}
    private class CommandWrapper implements Runnable {

        private ICommand command;

        public CommandWrapper(ICommand command) {
            this.command = command;
        }

        public void run() {
            // Keep it like this. First remove, then run. It's necessary in case  a command want to
            // rerun itself.
            commandMap.remove(command);
            command.run();
        }
    }
}
