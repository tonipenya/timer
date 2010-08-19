/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tonipenya
 */
public class TimerManagerTest {
    // TODO:  Design a running test case (with a receiver class or something) to check tha a set of tasks are executed in the proper order.

    private static final long MARGIN = (long) 1.1;
    ITask[] tasks;
    TestCommand[] commands;
    ITimerManager manager;

    @Before
    public void setup() {
        tasks = new ITask[3];
        tasks[0] = new Task(0, "task0", 1000);
        tasks[1] = new Task(1, "task1", 2000);
        tasks[2] = new Task(2, "task2", 3000);

        manager = new TimerManager();

        commands = new TestCommand[3];
        commands[0] = new TestCommand(manager, tasks[0]);
        commands[1] = new TestCommand(manager, tasks[1]);
        commands[2] = new TestCommand(manager, tasks[2]);

        tasks[0].setCommand(commands[0]);
        tasks[2].setCommand(commands[1]);
        tasks[1].setCommand(commands[2]);

    }

    @Test
    public void testStartTask() {
        System.out.println("startTask");
        TimerManager instance = new TimerManager();
        ITask task = tasks[0];

        instance.startTimer(task);

        assertTrue(instance.isTaskRunning(task));
    }

    @Test
    public void testStopTask() {
        System.out.println("stopTask");

        manager.startTimer(tasks[0]);
        manager.stopTimer(tasks[0]);

        assertFalse(manager.isTaskRunning(tasks[0]));
    }

    @Test
    public void testCommandCalled() {
        ITask task = tasks[0];

        manager.startTimer(task);

        pause(task.getInterval() * MARGIN);

        assertTrue(commands[0].isCalled());
    }

    @Test
    public void testIsTaskRunning() {
        ITask task = tasks[0];

        assertFalse(manager.isTaskRunning(task));

        manager.startTimer(task);

        assertTrue(manager.isTaskRunning(task));

        pause(task.getInterval() * MARGIN);

        assertFalse(manager.isTaskRunning(task));
    }

    @Test
    public void testGetTimeRemaining() {
        System.out.println("getTimeRemaining");
        ITask task = tasks[0];

        assertEquals(0, manager.getTimeRemaining(task));

        manager.startTimer(task);
        assertTrue(manager.getTimeRemaining(task) > 0);
        assertTrue(manager.getTimeRemaining(task) <= task.getInterval());

        pause(task.getInterval() * MARGIN);

        assertTrue(manager.getTimeRemaining(task) < task.getInterval());
        assertTrue(manager.getTimeRemaining(task) == 0);
    }

    private void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimerManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class TestCommand implements Runnable {

        private boolean called = false;
        private ITimerManager manager;
        private ITask task;

        public TestCommand(ITimerManager manager, ITask task) {
            this.manager = manager;
            this.task = task;
        }

        public boolean isCalled() {
            return called;
        }

        public void run() {
            called = true;
            manager.stopTimer(task);
        }
    }
}
