/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.listenermanagement.repository.api;

import net.danielkvasnicka.cloudscrobbler.listenermanagement.domain.Listener;
import net.danielkvasnicka.cloudscrobbler.web.auth.aop.LastFmSessionActive;

/**
 *
 * @author daniel
 */
public interface ListenerRepository {
    
    Listener findListener(String lastFmSessionKey);
    
    @LastFmSessionActive
    void saveListener(Listener listener);
}
