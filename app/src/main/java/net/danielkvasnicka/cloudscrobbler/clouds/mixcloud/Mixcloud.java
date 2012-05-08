/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.clouds.mixcloud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain.Listens;
import net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain.Mix;
import net.danielkvasnicka.cloudscrobbler.engine.api.NewTracks;
import net.danielkvasnicka.cloudscrobbler.engine.api.Track;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.domain.Listener;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.repository.api.ListenerRepository;
import net.danielkvasnicka.cloudscrobbler.utils.Utils;

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
            List<Mix> mixes = entity.getData();
            
            if (!mixes.isEmpty()) {

                // update listener
                listener.setLastScrobbledItemTimestamp(Utils.getListenTimeForNewestMix(mixes));
                this.repository.saveListener(listener);

                // get tracks from every mix
                final List<Track> tracksToScrobble = new ArrayList<Track>();
                for (Mix mix : mixes) {                
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
}
