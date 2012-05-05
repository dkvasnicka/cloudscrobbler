/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.web;

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
    
    @PostConstruct
    public void initListener() {
        this.listener = new Listener();
    }
    
    public void save() {
        
        this.listenerRepository.saveListener(this.listener);
    }
    
    // ----- get / set ------
    
    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
    
    
}
