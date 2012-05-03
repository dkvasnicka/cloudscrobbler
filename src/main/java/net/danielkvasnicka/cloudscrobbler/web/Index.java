/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.web;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.seam.rest.client.RestClient;

/**
 *
 * @author daniel
 */
@ManagedBean
public class Index {
    
    @Inject @RestClient("http://api.mixcloud.com/dkvasnicka/listens/")
    private ClientRequest request;
    
    public String getString() throws Exception {
        request.accept("text/javascript");
        ClientResponse<String> response = request.get(String.class);
        return response.getEntity();
    }
    
}
