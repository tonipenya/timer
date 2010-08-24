/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tonipenya.timer;

/**
 *
 * @author tonipenya
 */
public class TestCommand implements ICommand {

    public boolean called;

    public void run() {
        called = true;
    }

    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
