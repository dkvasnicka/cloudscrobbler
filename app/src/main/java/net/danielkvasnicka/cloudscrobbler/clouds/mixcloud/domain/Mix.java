/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import net.danielkvasnicka.cloudscrobbler.engine.api.Track;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import static org.apache.commons.collections.CollectionUtils.*;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;

/**
 *
 * @author daniel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mix {
    
    private String key;
    
    private List<MixSection> sections;
    
    @JsonProperty("listen_time")
    private Date listenTime;

    public List<MixSection> getSections() {
        return sections;
    }

    public Collection<Track> getTracks() {
        
    }
    
    public void setSections(List<MixSection> sections) {
        this.sections = sections;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getListenTime() {
        return listenTime;
    }

    public void setListenTime(Date listenTime) {
        this.listenTime = listenTime;
    }        
    
}
