/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.clouds.mixcloud;

import java.net.URI;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain.Listens;
import net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain.Mix;
import net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.restclientsupport.MixcloudJsonBodyReader;
import net.danielkvasnicka.cloudscrobbler.engine.api.NewTracks;
import net.danielkvasnicka.cloudscrobbler.engine.api.Track;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.domain.Listener;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.repository.api.ListenerRepository;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.seam.rest.client.RestClient;

/**
 *
 * @author daniel
 */
@Singleton
public class Mixcloud {
    
    private static final String BASE_API_URL = "http://api.mixcloud.com";
    
    @Inject
    private Event<NewTracks> event;

    @Inject @RestClient(BASE_API_URL)
    private Instance<ClientRequest> request;

    @Inject
    private ListenerRepository repository;
    
    @PostConstruct
    public void setupResteasy() {
        ResteasyProviderFactory.getInstance().addMessageBodyReader(MixcloudJsonBodyReader.class);        
    }

    //@Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    public void checkForNewListens() throws Exception {        
        Iterator<Listener> listeners = this.repository.getAllActiveListeners().iterator();
        
        while (listeners.hasNext()) {
            final Listener listener = listeners.next();            
            Date lastScrobbledItemTimestamp = listener.getLastScrobbledItemTimestamp();
            long since = lastScrobbledItemTimestamp == null ? 0 : lastScrobbledItemTimestamp.getTime() + 1;
            
            ClientRequest req = this.request.get();
            req.overrideUri(URI.create(String.format(BASE_API_URL + "/%s/listens/", listener.getCloudId())));
            req.queryParameter("since", since);
            ClientResponse<Listens> response = req.get(Listens.class);
            Listens entity = response.getEntity(Listens.class, Listens.class);
            
            final List<Track> tracksToScrobble = new ArrayList<Track>();
            for (Mix mix : entity.getData()) {
                ClientRequest tracksReq = this.request.get();
                tracksReq.overrideUri(URI.create(BASE_API_URL + mix.getKey()));
                ClientResponse<Mix> tracksResponse = tracksReq.get(Mix.class);
                mix = tracksResponse.getEntity(Mix.class, Mix.class);                
                
                tracksToScrobble.addAll(mix.getTracks());
            }
            
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
