/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.clouds.mixcloud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Singleton;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain.Listens;
import net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain.Mix;
import net.danielkvasnicka.cloudscrobbler.engine.api.NewTracks;
import net.danielkvasnicka.cloudscrobbler.engine.api.Track;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.domain.Listener;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.repository.api.ListenerRepository;

/**
 *
 * @author daniel
 */
@Singleton
public class Mixcloud {
    
    @Inject
    private Event<NewTracks> event;

    @Inject
    private ListenerRepository repository;

    @Inject
    private MixcloudRESTClient client;
    
    //@Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    public void checkForNewListens() throws Exception {        
        Iterator<Listener> listeners = this.repository.getAllActiveListeners().iterator();
        
        while (listeners.hasNext()) {
            final Listener listener = listeners.next();            
            
            // get listens
            Listens entity = this.client.getListensFor(listener);
            
            // get tracks from every mix
            final List<Track> tracksToScrobble = new ArrayList<Track>();
            for (Mix mix : entity.getData()) {                
                tracksToScrobble.addAll(this.client.getMixWithSections(mix).getTracks());
            }
            
            // fire event
            this.event.fire(new NewTracks() {

                public String getLastFmSessionKey() {
                    return listener.getLastFmSessionKey();
                }

                public Collection<Track> getNewTracks() {
                    return tracksToScrobble;
                }
            });
        }
        
        
    }
}
