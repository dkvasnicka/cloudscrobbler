/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.listenermanagement.repository.api;

import net.danielkvasnicka.cloudscrobbler.listenermanagement.domain.Listener;

/**
 *
 * @author daniel
 */
public interface ListenerRepository {
    
    Listener findListener(String lastFmSessionKey);
    
    void saveListener(Listener listener);
    
    Iterable<Listener> getAllActiveListeners();
}
