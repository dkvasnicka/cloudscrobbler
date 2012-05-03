/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.domain.mixcloud;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author daniel
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Mix {
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
