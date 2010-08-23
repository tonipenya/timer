/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tonipenya;

import com.tonipenya.TimerManagerTest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tonipenya
 */
public class Util {
    public static final void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimerManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
