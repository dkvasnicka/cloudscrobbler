/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain;

import java.util.Map;
import net.danielkvasnicka.cloudscrobbler.engine.api.Track;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author daniel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MixTrack implements Track {

    private String name;
    
    @JsonProperty("artist")
    private Map artistInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map getArtistInfo() {
        return artistInfo;
    }

    public void setArtistInfo(Map artistInfo) {
        this.artistInfo = artistInfo;
    }

    public String getArtist() {
        return (String) this.artistInfo.get("name");
    }

    @Override
    public String toString() {
        return this.getArtist() + " - " + this.getName();
    }


}
