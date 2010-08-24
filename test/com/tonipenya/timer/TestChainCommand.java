/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tonipenya.timer;

import com.tonipenya.timer.dmo.ChainedTask;

/**
 *
 * @author tonipenya
 */
public class TestChainCommand extends ChainedCommand{

    public TestChainCommand(ChainedTask task, ITimerManager manager) {
        super(task, manager);
    }

    @Override
    protected void implementation() {
        System.out.println("Run!");
    }

}
