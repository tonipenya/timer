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

    int getId();

    String getName();

    long getInterval();

    void run();
}
