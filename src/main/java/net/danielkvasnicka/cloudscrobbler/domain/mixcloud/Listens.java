/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.domain.mixcloud;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author daniel
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Listens {
    
    private List<Mix> data;

    public List<Mix> getData() {
        return data;
    }

    public void setData(List<Mix> data) {
        this.data = data;
    }
    
    
}
