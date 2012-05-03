/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.engine;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author daniel
 */
@Singleton
public class Scrobbler {
    
    //@Schedule(second="*/1", minute="*",hour="*", persistent=false)
    public void test() {
        System.out.println("Hi!");
    }
}
