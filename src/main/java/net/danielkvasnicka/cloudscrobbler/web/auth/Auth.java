/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.web.auth;

import de.umass.lastfm.Session;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpSession;

/**
 *
 * @author daniel
 */
@Named @Singleton
public class Auth {
    
    @Inject
    private HttpSession session;
    
    @Produces @Named("lastFmSession")
    public Session getLastFmSession() {
        return (Session) this.session.getAttribute(LastfmCallbackHandler.LAST_FM_SESSION_ATTR);
    }
    
    public void logout() {
        this.session.removeAttribute(LastfmCallbackHandler.LAST_FM_SESSION_ATTR);
    }

}
