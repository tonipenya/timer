/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tonipenya
 */
public class TaskChainTest {
    private ITask[] tasks;
    private int id;
    private String name;
    private ITimerManager manager;

    @Before
    public void setup() {
        id = 124;
        name = "task chain";

        tasks = new ITask[3];
        tasks[0] = new Task(41, "asoenu", 1234);
        tasks[1] = new Task(12, "aoeuaa", 4133);
        tasks[2] = new Task(51, "uaeaeu", 5123);

        manager = new TimerManager();
    }

    @Test
    public void testNameNotStarted() {
        // Test chain without tasks
        TaskChain tc = new TaskChain(id, name);
        assertEquals(name, tc.getName());

        // Test chain with tasks
        tc = new TaskChain(id, name);
        tc.setTasks(tasks);

        assertEquals(name, tc.getName());
    }

    @Test
    public void testNameStarted() {
        fail("Test prototype");
    }

    @Test
    public void testIntervalNotStarted() {
        fail("Test prototype");
    }

    @Test
    public void testIntervalStarted() {
        fail("Test prototype");
    }

    @Test
    public void testRun() {
        fail("Test prototype");
    }
}
