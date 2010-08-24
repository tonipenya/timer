/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya.timer;

import com.tonipenya.util.Util;
import com.tonipenya.timer.dmo.ITask;
import com.tonipenya.timer.dmo.Task;
import com.tonipenya.timer.dmo.ChainedTask;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tonipenya
 */
public class ChainTimerTaskTest {

    // TODO: Split htis test into more feature-specific ones.
    @Test
    public void testRun() {
        int[] ids = {0, 1, 2};
        String[] names = {"task0", "task1", "task2"};
        int[] intervals = {100, 200, 300};
        ITask[] tasks = new ITask[ids.length];

        for (int i = 0; i < ids.length; i++) {
            tasks[i] = new Task(ids[i], names[i], intervals[i]);
        }

        int id = 14242;
        String name = "chainned task";
        ChainedTask chained = new ChainedTask(id, name, tasks);

        ITimerManager instance = new TimerManager();
        ChainedCommand command = new TestChainCommand(chained, instance);
        instance.startTimer(command, chained.getTasks()[0].getInterval());

        Util.pause(intervals[0] / 3);

        for (int i = 0; i < ids.length; i++) {
            assertTrue(instance.isRunning(command));
            assertTrue(0 < instance.getTimeRemaining(command));
            assertTrue(instance.getTimeRemaining(command) < intervals[i]);
            assertEquals(name + " - " + names[i], command.getName());

            Util.pause(intervals[i]);
        }

        assertFalse(instance.isRunning(command));

        boolean exceptionthrown = false;

        try {
            instance.getTimeRemaining(command);
        } catch (UnsupportedOperationException uoe) {
            exceptionthrown = true;
        }

        assertTrue(exceptionthrown);

        // Not sure about this constraint
        assertEquals(name, command.getName());
    }

    @Test
    public void testStopTimer() {
        int[] ids = {0, 1, 2};
        String[] names = {"task0", "task1", "task2"};
        int[] intervals = {100, 200, 300};
        ITask[] tasks = new ITask[ids.length];

        for (int i = 0; i < ids.length; i++) {
            tasks[i] = new Task(ids[i], names[i], intervals[i]);
        }

        int id = 14242;
        String name = "chainned task";
        ChainedTask chained = new ChainedTask(id, name, tasks);

        ITimerManager instance = new TimerManager();
        ChainedCommand command = new TestChainCommand(chained, instance);
        instance.startTimer(command, chained.getTasks()[0].getInterval());

        Util.pause(intervals[0] / 3);

        instance.stopTimer(command);

        assertFalse(instance.isRunning(command));
    }
}
