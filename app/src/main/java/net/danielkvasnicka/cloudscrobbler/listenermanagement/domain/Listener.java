/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.listenermanagement.domain;

import java.util.Date;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 *
 * @author daniel
 */
public class Listener {
    public static final String EUROPE_LONDON_TZ = "Europe/London";
        
    private ObjectId _id;
    
    private boolean enableScrobbling;
    
    private String cloudId;

    private String lastFmId;

    private String lastFmSessionKey;
    
    private Date lastScrobbledItemTimestamp;

    public String getLastFmId() {
        return lastFmId;
    }

    public void setLastFmId(String lastFmId) {
        this.lastFmId = lastFmId;
    }

    public Date getLastScrobbledItemTimestamp() {
        return lastScrobbledItemTimestamp;
    }

    public void setLastScrobbledItemTimestamp(Date lastScrobbledItemTimestamp) {
        this.lastScrobbledItemTimestamp = lastScrobbledItemTimestamp;
    }        
    
    public boolean isEnableScrobbling() {
        return enableScrobbling;
    }

    public void setEnableScrobbling(boolean enableScrobbling) {
        this.enableScrobbling = enableScrobbling;

        if (enableScrobbling && !this.enableScrobbling) {
            this.setLastScrobbledItemTimestamp(
                    new DateTime().withZone(DateTimeZone.forID(EUROPE_LONDON_TZ)).toDate());
        }
    }

    public String getCloudId() {
        return cloudId;
    }

    public void setCloudId(String cloudId) {
        this.cloudId = cloudId;
    }

    public String getLastFmSessionKey() {
        return lastFmSessionKey;
    }

    public void setLastFmSessionKey(String lastFmSessionKey) {
        this.lastFmSessionKey = lastFmSessionKey;
    }        
}
