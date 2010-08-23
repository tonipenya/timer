/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tonipenya;

import com.tonipenya.ChainedCommand;
import com.tonipenya.ITimerManager;
import com.tonipenya.ChainedTask;

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
