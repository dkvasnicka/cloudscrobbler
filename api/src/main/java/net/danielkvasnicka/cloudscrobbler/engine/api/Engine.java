/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.engine.api;

import net.danielkvasnicka.cloudscrobbler.engine.api.NewTracks;

/**
 *
 * @author daniel
 */
public interface Engine {

    void scrobble(NewTracks event);
}
