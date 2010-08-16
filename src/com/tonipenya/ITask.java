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

    void execute();

    int getId();

    String getName();

    // TODO: Rename to getRemaining (or something).
    long getInterval();
}
