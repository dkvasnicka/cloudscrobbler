/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author daniel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MixSection {
    private static final String TYPE_TRACK = "track";
    
    @JsonProperty("section_type")
    private String type;
    
    private MixTrack track;

    public MixTrack getTrack() {
        return track;
    }

    public void setTrack(MixTrack track) {
        this.track = track;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public boolean isTrack() {
        return TYPE_TRACK.equals(this.type);
    }
}
