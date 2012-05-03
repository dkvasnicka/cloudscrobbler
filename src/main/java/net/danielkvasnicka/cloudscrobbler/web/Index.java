/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.web;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import net.danielkvasnicka.cloudscrobbler.domain.mixcloud.Listens;
import net.danielkvasnicka.cloudscrobbler.restclient.MixcloudJsonBodyReader;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.seam.rest.client.RestClient;

/**
 *
 * @author daniel
 */
@ManagedBean
public class Index {
    
    @Inject @RestClient("http://api.mixcloud.com/dkvasnicka/listens/")
    private ClientRequest request;
    
    public Listens getListens() throws Exception {
        ResteasyProviderFactory.getInstance().addMessageBodyReader(MixcloudJsonBodyReader.class);
        this.request.accept("text/javascript");
        ClientResponse<Listens> response = this.request.get(Listens.class);
        return response.getEntity(Listens.class, Listens.class);
    }
    
}
