/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.engine;

import de.umass.lastfm.Authenticator;
import de.umass.lastfm.Session;
import de.umass.lastfm.scrobble.ScrobbleData;
import de.umass.lastfm.scrobble.ScrobbleResult;
import java.util.List;
import java.util.Properties;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import net.danielkvasnicka.cloudscrobbler.engine.api.NewTracks;
import net.danielkvasnicka.cloudscrobbler.utils.Utils;
import org.jboss.solder.resourceLoader.Resource;

/**
 *
 * @author daniel
 */
@Stateless
public class Engine {

    @Inject
    @Resource("META-INF/lastfm.properties")
    private Properties lastFmCredentials;

    @Asynchronous
    public void scrobble(@Observes NewTracks event) {
        Session session = Authenticator.getSession(event.getLastFmSessionKey(),
                this.lastFmCredentials.getProperty("lastfm.apikey"),
                this.lastFmCredentials.getProperty("lastfm.secret"));

        List<ScrobbleResult> result = de.umass.lastfm.Track.scrobble(
                (List<ScrobbleData>) Utils.transformTracksToScrobbleData(event.getNewTracks()), session);
        Utils.logFailedScrobbleAttempts(result, session.getUsername());
    }
}
