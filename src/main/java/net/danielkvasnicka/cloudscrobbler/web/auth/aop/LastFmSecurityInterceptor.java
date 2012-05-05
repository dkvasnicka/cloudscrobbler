/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.web.auth.aop;

import de.umass.lastfm.Session;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author daniel
 */
@Interceptor
@LastFmSessionActive
public class LastFmSecurityInterceptor {
    
    @Inject
    private Session lastFmSession;
    
    @AroundInvoke
    public Object checkLastFmSession(InvocationContext joinPoint) throws Exception {
        
        if (this.lastFmSession == null || this.lastFmSession.getKey() == null) {
            throw new IllegalStateException("There is no valid Last.fm session!");
        }
        
        return joinPoint.proceed();
    }
}
