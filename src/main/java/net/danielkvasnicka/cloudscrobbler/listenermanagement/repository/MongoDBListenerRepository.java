/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.listenermanagement.repository;

import javax.inject.Named;
import javax.inject.Singleton;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.domain.Listener;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.repository.api.ListenerRepository;

/**
 *
 * @author daniel
 */
@Named @Singleton
public class MongoDBListenerRepository implements ListenerRepository {

    public Listener findListener(String lastFmSessionKey) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void saveListener(Listener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
