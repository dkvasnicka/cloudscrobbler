/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.engine.api;

import java.util.Collection;

/**
 *
 * @author daniel
 */
public interface NewTracks {

    String getLastFmSessionKey();
    
    Collection<Track> getNewTracks();
}
