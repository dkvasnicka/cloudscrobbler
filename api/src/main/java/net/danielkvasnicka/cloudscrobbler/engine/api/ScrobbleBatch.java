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
public class ScrobbleBatch {


    private String lastFmSessionKey;

    private Collection<Track> newTracks;

    public ScrobbleBatch(String lastFmSessionKey, Collection<Track> newTracks) {
        this.lastFmSessionKey = lastFmSessionKey;
        this.newTracks = newTracks;
    }

    public String getLastFmSessionKey() {
        return lastFmSessionKey;
    }

    public void setLastFmSessionKey(String lastFmSessionKey) {
        this.lastFmSessionKey = lastFmSessionKey;
    }

    public Collection<Track> getNewTracks() {
        return newTracks;
    }

    public void setNewTracks(Collection<Track> newTracks) {
        this.newTracks = newTracks;
    }


}
