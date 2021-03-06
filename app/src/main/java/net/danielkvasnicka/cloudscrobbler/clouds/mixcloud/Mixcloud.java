/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.clouds.mixcloud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain.Listens;
import net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain.Mix;
import net.danielkvasnicka.cloudscrobbler.engine.api.NewTracks;
import net.danielkvasnicka.cloudscrobbler.engine.api.ScrobbleBatch;
import net.danielkvasnicka.cloudscrobbler.engine.api.Track;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.domain.Listener;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.repository.api.ListenerRepository;
import net.danielkvasnicka.cloudscrobbler.utils.Utils;
import org.jboss.solder.logging.Category;
import org.jboss.solder.logging.Logger;

/**
 *
 * @author daniel
 */
@Stateless
public class Mixcloud {
    
    @Inject
    private Event<NewTracks> event;

    @Inject
    private ListenerRepository repository;

    @Inject
    private MixcloudRESTClient client;
    
    @Inject @Category("Mixcloud")
    private Logger logger;

    @Schedule(minute = "*/30", hour = "*", persistent = false)
    public void checkForNewListens() throws Exception {
        this.logger.info("Running Mixcloud scanner to determine new listens.");
        Iterator<Listener> listeners = this.repository.getAllActiveListeners().iterator();

        Collection<ScrobbleBatch> batches = new ArrayList<ScrobbleBatch>();
        while (listeners.hasNext()) {
            final Listener listener = listeners.next();            
            this.logger.debug("Checking for new listens for " + listener.getCloudId());

            // get listens
            Listens entity = this.client.getListensFor(listener);
            List<Mix> mixes = entity.getData();
            this.logger.debug("Found " + mixes.size() + " new mixes.");

            if (!mixes.isEmpty()) {

                // update listener
                listener.setLastScrobbledItemTimestamp(Utils.getListenTimeForNewestMix(mixes));
                this.repository.saveListener(listener);

                // get tracks from every mix
                final List<Track> tracksToScrobble = new ArrayList<Track>();
                for (Mix mix : mixes) {                
                    tracksToScrobble.addAll(this.client.getMixWithSections(mix).getTracks());
                }

                batches.add(new ScrobbleBatch(listener.getLastFmSessionKey(), tracksToScrobble));
            }
        }
        
        // fire event
        if (!batches.isEmpty()) {
            this.event.fire(new NewTracks(batches));
        }
    }
}
