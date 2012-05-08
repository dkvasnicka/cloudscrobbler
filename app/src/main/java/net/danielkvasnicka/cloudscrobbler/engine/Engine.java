/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.engine;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import net.danielkvasnicka.cloudscrobbler.engine.api.NewTracks;

/**
 *
 * @author daniel
 */
@Stateless
public class Engine {

    @Asynchronous
    public void scrobble(@Observes NewTracks event) {
        System.out.println("class: " + this.hashCode());
        System.out.println("--------- Got " + event);
    }
}
