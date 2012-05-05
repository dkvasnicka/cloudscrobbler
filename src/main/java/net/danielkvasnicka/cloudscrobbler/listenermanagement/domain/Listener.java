/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.listenermanagement.domain;

/**
 *
 * @author daniel
 */
public class Listener {
        
    private boolean enableScrobbling;
    
    private String cloudId;

    private String lastFmSessionKey;
    
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
