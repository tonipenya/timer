/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tonipenya;

/**
 *
 * @author tonipenya
 */
public interface ITask {

    void run();

    int getId();

    String getName();

    long getInterval();
}
