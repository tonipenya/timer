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
public class ChainTimerTaskTest {

    private ITask[] tasks;
    private Runnable[] commands;
    private int id;
    private String name;
    private ITimerManager manager;
    private long totalInterval;

    @Before
    public void setup() {
        tasks = new ITask[3];
        tasks[0] = new Task(0, "task0", 1000);
        tasks[1] = new Task(1, "task1", 2000);
        tasks[2] = new Task(2, "task2", 3000);

        manager = new TimerManager();

        commands = new Runnable[3];
        commands = new TestCommand[3];
        commands[0] = new TestCommand(manager, tasks[0]);
        commands[1] = new TestCommand(manager, tasks[1]);
        commands[2] = new TestCommand(manager, tasks[2]);

        tasks[0].setCommand(commands[0]);
        tasks[2].setCommand(commands[1]);
        tasks[1].setCommand(commands[2]);



        id = 123;
        name = "task chain";

//        tasks = new ITask[3];
//        tasks[0] = new Task(1, "task 1", 100);
//        tasks[1] = new Task(2, "task 2", 200);
//        tasks[2] = new Task(3, "task 3", 300);

        totalInterval = 0;
        for (ITask task : tasks) {
            totalInterval += task.getInterval();
        }
        
        manager = new TimerManager();
    }

    @Test
    public void testConstructor() {
        ChainedTask tc;

        boolean exceptionThrown = false;

        try {
            tc = new ChainedTask(id, null, null);
        } catch (UnsupportedOperationException uoe) {
            exceptionThrown = true;
        }

        if (!exceptionThrown) {
            fail("An exception was expected");
        }

        exceptionThrown = false;

        try {
            tc = new ChainedTask(id, null, tasks);
        } catch (UnsupportedOperationException uoe) {
            exceptionThrown = true;
        }

        if (!exceptionThrown) {
            fail("An exception was expected");
        }
        exceptionThrown = false;

        try {
            tc = new ChainedTask(id, name, null);
        } catch (UnsupportedOperationException uoe) {
            exceptionThrown = true;
        }

        if (!exceptionThrown) {
            fail("An exception was expected");
        }

        tc = new ChainedTask(id, name, tasks);

        assertEquals(id, tc.getId());
        assertEquals(name, tc.getName());

    }

    @Test
    public void testRun() {
        ChainedTask tc = new ChainedTask(id, name, tasks);
        tc.setCommand(new ChainCommand(tc));
        // Before running
//        assertEquals(id, manager.getRunningInstance(tc).getId());
//        assertEquals(name, manager.getRunningInstance(tc).getName());
        assertEquals(0, manager.getTimeRemaining(tc));

        // During run
        manager.startTimer(tc);
        pause(50);

        for (ITask task : tasks) {
//            assertEquals(name + " - " + task.getName(), manager.getRunningInstance(tc).getName());
            
            assertTrue(0 <= manager.getTimeRemaining(tc));
            System.out.println(manager.getTimeRemaining(tc) + " <= " + task.getInterval());
            assertTrue(manager.getTimeRemaining(tc) <= task.getInterval());

//            assertEquals(id, manager.getRunningInstance(tc).getId());
            pause(task.getInterval());
        }

        // After Run
//        assertEquals(name, manager.getRunningInstance(tc).getName());
        assertEquals(totalInterval, manager.getTimeRemaining(tc));
    }

    @Test
    public void testRunEmptyTasks() {
        fail("Test prototype");
    }

    private void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimerManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // TODO: Extract this class
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

    private class ChainCommand implements Runnable {
        private ChainedTask task;
        private int current;

        public ChainCommand(ChainedTask task) {
            current = 0;
            this.task = task;
        }

        public void run() {
            task.getTasks()[current].getCommand().run();

            current++;

            if (current < task.getTasks().length) {
                // Go ahead
            } else {
                // Stop
            }
        }

    }

}
