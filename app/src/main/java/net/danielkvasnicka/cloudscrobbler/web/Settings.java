/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.web;

import de.umass.lastfm.Session;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.domain.Listener;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.repository.api.ListenerRepository;
import net.danielkvasnicka.cloudscrobbler.web.auth.aop.LastFmSessionActive;

/**
 *
 * @author daniel
 */
@Named
@RequestScoped
@LastFmSessionActive
public class Settings {

    @Inject
    private ListenerRepository listenerRepository;
    
    private Listener listener;
    
    @Inject
    private Session lastFmSession;
    
    @PostConstruct
    public void initListener() {
        Listener savedListener = this.listenerRepository.findListener(this.lastFmSession.getKey());
        this.listener = savedListener == null ? new Listener() : savedListener;
    }
    
    public void save() {        
        String key = this.lastFmSession.getKey();
        if (this.listener.getLastFmSessionKey() == null) {            
            this.listener.setLastFmSessionKey(key);
        }
        this.listenerRepository.saveListener(this.listener);
    }
    
    // ----- get / set ------
    
    public Listener getListener() {
        return listener;
    }
}