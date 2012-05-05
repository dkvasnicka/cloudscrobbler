/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.listenermanagement.repository;

import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.config.Collection;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.domain.Listener;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.repository.api.ListenerRepository;
import org.jongo.MongoCollection;

/**
 *
 * @author daniel
 */
@Named @Singleton
public class MongoDBListenerRepository implements ListenerRepository {

    @Inject @Collection("listeners")
    private MongoCollection listeners;
    
    public Listener findListener(String lastFmSessionKey) {
        return this.listeners.findOne(
                "{ lastFmSessionKey : # }", lastFmSessionKey).as(Listener.class);
    }

    public void saveListener(Listener listener) {
        try {
            this.listeners.save(listener);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
