/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain;

import java.util.Collections;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author daniel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Listens {
    
    private List<Mix> data;

    public List<Mix> getData() {
        if (this.data == null) {
            this.data = Collections.EMPTY_LIST;
        }
        
        return data;
    }

    public void setData(List<Mix> data) {
        this.data = data;
    }
    
    
}
