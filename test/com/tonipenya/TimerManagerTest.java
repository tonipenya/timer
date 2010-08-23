/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya;

import com.tonipenya.TimerManager;
import com.tonipenya.ICommand;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tonipenya
 */
public class TimerManagerTest {

    private static final float MARGIN = 1.1f;

    @Test
    public void testStartTask() {
        TimerManager instance = new TimerManager();
        ICommand command = new TestCommand();

        instance.startTimer(command, 1000);

        assertTrue(instance.isRunning(command));
    }

    @Test
    public void testStopTask() {
        TimerManager instance = new TimerManager();
        ICommand command = new TestCommand();

        instance.startTimer(command, 1000);
        instance.stopTimer(command);

        assertFalse(instance.isRunning(command));
        assertFalse(((TestCommand) command).called);
    }

    @Test
    public void testCommandCalled() {
        TimerManager instance = new TimerManager();
        long interval = 100;
        ICommand command = new TestCommand();

        instance.startTimer(command, interval);

        Util.pause((long) (interval * MARGIN));

        assertTrue(((TestCommand) command).called);
    }

    @Test
    public void testIsRunning() {
        TimerManager instance = new TimerManager();
        long interval = 1000;
        ICommand command = new TestCommand();

        assertFalse(instance.isRunning(command));

        instance.startTimer(command, interval);

        assertTrue(instance.isRunning(command));

        Util.pause((long) (interval * MARGIN));

        assertFalse(instance.isRunning(command));
    }

    @Test
    public void testGetTimeRemaining() {
        TimerManager instance = new TimerManager();
        long interval = 1000;
        ICommand command = new TestCommand();

        boolean exceptionthrown = false;

        try {
            instance.getTimeRemaining(command);
        } catch (UnsupportedOperationException uoe) {
            exceptionthrown = true;
        }

        assertTrue(exceptionthrown);

        instance.startTimer(command, interval);

        assertTrue(instance.getTimeRemaining(command) > 0);
        assertTrue(instance.getTimeRemaining(command) < interval);

        Util.pause((long) (interval * MARGIN));

        exceptionthrown = false;

        try {
            instance.getTimeRemaining(command);
        } catch (UnsupportedOperationException uoe) {
            exceptionthrown = true;
        }

        assertTrue(exceptionthrown);
    }

    @Test
    public void testGetRunning() {
        TimerManager instance = new TimerManager();
        ICommand command0 = new TestCommand();
        ICommand command1 = new TestCommand();
        ICommand command2 = new TestCommand();
        long internval = 100;

        instance.startTimer(command0, internval);
        assertTrue(instance.getRunning().contains(command0));
        assertFalse(instance.getRunning().contains(command1));
        assertFalse(instance.getRunning().contains(command2));
        assertEquals(1, instance.getRunning().size());

        Util.pause((long) (internval * 0.3));

        instance.startTimer(command1, internval);
        assertTrue(instance.getRunning().contains(command0));
        assertTrue(instance.getRunning().contains(command1));
        assertFalse(instance.getRunning().contains(command2));
        assertEquals(2, instance.getRunning().size());

        Util.pause((long) (internval * 0.3));

        instance.startTimer(command2, internval);
        assertTrue(instance.getRunning().contains(command0));
        assertTrue(instance.getRunning().contains(command1));
        assertTrue(instance.getRunning().contains(command2));
        assertEquals(3, instance.getRunning().size());

        Util.pause((long) (internval * 0.5));

        assertFalse(instance.getRunning().contains(command0));
        assertTrue(instance.getRunning().contains(command1));
        assertTrue(instance.getRunning().contains(command2));
        assertEquals(2, instance.getRunning().size());

        Util.pause((long) (internval * 0.3));

        assertFalse(instance.getRunning().contains(command0));
        assertFalse(instance.getRunning().contains(command1));
        assertTrue(instance.getRunning().contains(command2));
        assertEquals(1, instance.getRunning().size());

        Util.pause((long) (internval * 0.3));

        assertFalse(instance.getRunning().contains(command0));
        assertFalse(instance.getRunning().contains(command1));
        assertFalse(instance.getRunning().contains(command2));
        assertEquals(0, instance.getRunning().size());
    }
}
