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
public class NewTracks {

    private Collection<ScrobbleBatch> batches;

    public NewTracks(Collection<ScrobbleBatch> batches) {
        this.batches = batches;
    }

    public Collection<ScrobbleBatch> getBatches() {
        return batches;
    }
}
