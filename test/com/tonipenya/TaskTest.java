/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tonipenya;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tonipenya
 */
// TODO: rename to TaskTest
public class TaskTest {

    @Test
    public void testInterval() {
        System.out.println("Interval");
        long seconds = 123456487L;
        Task instance = new Task(124, null, seconds);

        assertEquals(seconds, instance.getInterval());
    }

    @Test
    public void testName() {
        System.out.println("name");
        String name = "TestName";
        Task instance = new Task(622, name, 0);

        assertEquals(name, instance.getName());
    }

    // TODO: test equals method

}