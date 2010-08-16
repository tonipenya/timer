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
    private int id;
    private String name;
    private ITimerManager manager;
    private long totalInterval;

    @Before
    public void setup() {
        id = 123;
        name = "task chain";

        tasks = new ITask[3];
        tasks[0] = new Task(1, "task 1", 100);
        tasks[1] = new Task(2, "task 2", 200);
        tasks[2] = new Task(3, "task 3", 300);

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
        // Before running
        assertEquals(id, manager.getRunningInstance(tc).getId());
        assertEquals(name, manager.getRunningInstance(tc).getName());
        assertEquals(totalInterval, manager.getRunningInstance(tc).getInterval());

        // During run
        manager.startTimer(tc);
        pause(50);

        for (ITask task : tasks) {
            System.out.println(name + " - " + task.getName() + ", " + manager.getRunningInstance(tc).getName());
            assertEquals(name + " - " + task.getName(), manager.getRunningInstance(tc).getName());
            
            System.out.println(0 + " <= " + manager.getRunningInstance(tc).getInterval());
            assertTrue(0 <= manager.getRunningInstance(tc).getInterval());
            System.out.println(manager.getRunningInstance(tc).getInterval() + " <=  " + task.getInterval());
            assertTrue(manager.getRunningInstance(tc).getInterval() <= task.getInterval());

            assertEquals(id, manager.getRunningInstance(tc).getId());
            pause(task.getInterval());
        }

        // After Run
        assertEquals(name + " - " + tasks[0].getName(), manager.getRunningInstance(tc).getName());
        assertEquals(totalInterval, manager.getRunningInstance(tc).getInterval());
    }

    @Test
    public void testRunEmptyTasks() {
        fail("Test prototype");
    }

    private void pause(long ms) {
        try {
            System.out.println("ms: " + ms);
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimerManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private class Runner extends Thread {
        ITask task;

        public Runner(ITask task) {
            this.task = task;
        }

        @Override
        public void run() {
            manager.startTimer(task);
        }


    }
}
