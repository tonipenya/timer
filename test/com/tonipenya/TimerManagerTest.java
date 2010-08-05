/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya;

import java.util.HashSet;
import java.util.Set;
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
    // TODO:  Write a test for isTaskRunning method.

    ITask[] tasks;
    TimerManager manager;

    @Before
    public void setup() {
        tasks = new ITask[3];

        tasks[0] = new Task(0, "task0", 10);
        tasks[1] = new Task(1, "task1", 11);
        tasks[2] = new Task(2, "task2", 12);

        manager = new TimerManager();

        for (ITask task : tasks) {
            manager.startTimer(task);
        }
    }

    @Test
    public void testStartTask() {
        System.out.println("startTask");
        TimerManager instance = new TimerManager();
        ITask task = tasks[0];

        instance.startTimer(task);

        assertTrue(instance.getRunningTasks().contains(task));
    }

    @Test
    public void testStopTask() {
        System.out.println("stopTask");
        TimerManager instance = new TimerManager();

        instance.startTimer(tasks[0]);
        instance.stopTimer(tasks[0]);

        assertEquals(0, instance.getRunningTasks().size());
    }

    @Test
    public void testGetTimeRemaining() {
        System.out.println("getTimeRemaining");
        ITask task = new Task(13, "example", 1000);
        TimerManager instance = new TimerManager();
        assertEquals(0, instance.getTimeRemaining(task));
        instance.startTimer(task);

        try {
            Thread thread = new Thread();
            thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimerManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertTrue(instance.getTimeRemaining(task) < task.getInterval());
        assertTrue(instance.getTimeRemaining(task) > 0);
    }

    @Test
    public void testGetRunningTasks() {
        System.out.println("getRunningTasks");

        assertEquals(tasks.length, manager.getRunningTasks().size());

        for (ITask task : tasks) {
            assertTrue(manager.getRunningTasks().contains(task));
        }
    }
}
