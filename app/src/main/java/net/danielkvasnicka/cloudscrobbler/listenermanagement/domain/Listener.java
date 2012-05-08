/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.listenermanagement.domain;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author daniel
 */
public class Listener {
        
    private ObjectId _id;
    
    private boolean enableScrobbling;
    
    private String cloudId;

    private String lastFmSessionKey;
    
    private Date lastScrobbledItemTimestamp;

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
