/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.clouds.mixcloud;

import java.net.URI;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain.Listens;
import net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain.Mix;
import net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.restclientsupport.MixcloudJsonBodyReader;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.domain.Listener;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.seam.rest.client.RestClient;

/**
 *
 * @author daniel
 */
@Singleton
public class MixcloudRESTClient {    

    private static final String BASE_API_URL = "http://api.mixcloud.com";    

    @Inject @RestClient(BASE_API_URL)
    private Instance<ClientRequest> request;
    
    @PostConstruct
    public void setupResteasy() {
        ResteasyProviderFactory.getInstance().addMessageBodyReader(MixcloudJsonBodyReader.class);        
    }
    
    public Listens getListensFor(Listener listener) throws Exception {
        Date lastScrobbledItemTimestamp = listener.getLastScrobbledItemTimestamp();
        long since = lastScrobbledItemTimestamp == null ? 
                new Date().getTime() : lastScrobbledItemTimestamp.getTime() + 1;
        
        ClientRequest req = this.request.get();
        req.overrideUri(URI.create(String.format(BASE_API_URL + "/%s/listens/", listener.getCloudId())));
        req.queryParameter("since", since);
        ClientResponse<Listens> response = req.get(Listens.class);
        return response.getEntity(Listens.class, Listens.class);        
    }
    
    public Mix getMixWithSections(Mix mix) throws Exception {
        ClientRequest tracksReq = this.request.get();
        tracksReq.overrideUri(URI.create(BASE_API_URL + mix.getKey()));
        ClientResponse<Mix> tracksResponse = tracksReq.get(Mix.class);
        return tracksResponse.getEntity(Mix.class, Mix.class);                        
    }
}
