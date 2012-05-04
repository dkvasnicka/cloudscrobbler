/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.clouds.mixcloud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    
    @Inject
    private Event<NewTracks> event;

    @Inject @RestClient("http://api.mixcloud.com/dkvasnicka/listens/")
    private Instance<ClientRequest> request;

    @PostConstruct
    public void setupResteasy() {
        ResteasyProviderFactory.getInstance().addMessageBodyReader(MixcloudJsonBodyReader.class);        
    }

    @Schedule(minute = "*/1", hour = "*", persistent = false)
    public void test() throws Exception {        
        ClientRequest req = this.request.get();
        req.accept("text/javascript");
        ClientResponse<Listens> response = req.get(Listens.class);
        final Listens entity = response.getEntity(Listens.class, Listens.class);

        this.event.fire(new NewTracks() {

            public Collection<Track> getNewTracks() {
                List result = new ArrayList();
                for (final Mix object : entity.getData()) {
                    result.add(new Track() {

                        public String getArtist() {
                            return null;
                        }

                        public String getName() {
                            return object.getName();
                        }
                    });
                }

                return result;
            }
        });
    }
}
