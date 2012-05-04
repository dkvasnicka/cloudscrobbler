/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.engine;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import net.danielkvasnicka.cloudscrobbler.engine.api.NewTracks;
import net.danielkvasnicka.cloudscrobbler.engine.api.Track;

/**
 *
 * @author daniel
 */
@Singleton
public class Engine {

    public void scrobble(@Observes NewTracks event) {

        System.out.println("--------- Got " + event);
        for (Track t : event.getNewTracks()) {
            System.out.println(t.getName());
        }
    }
}
