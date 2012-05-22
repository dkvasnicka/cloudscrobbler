/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.engine;

import net.danielkvasnicka.cloudscrobbler.engine.api.Engine;
import de.umass.lastfm.Session;
import de.umass.lastfm.scrobble.ScrobbleData;
import de.umass.lastfm.scrobble.ScrobbleResult;
import java.util.List;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.danielkvasnicka.cloudscrobbler.engine.api.NewTracks;
import net.danielkvasnicka.cloudscrobbler.engine.api.ScrobbleBatch;
import net.danielkvasnicka.cloudscrobbler.utils.Utils;
import org.jboss.solder.resourceLoader.Resource;

/**
 *
 * @author daniel
 */
@ApplicationScoped
public class DefaultEngine implements Engine {

    @Inject
    @Resource("META-INF/lastfm.properties")
    private Properties lastFmCredentials;

    public void scrobble(@Observes NewTracks event) {
        for (ScrobbleBatch batch : event.getBatches()) {

            String lastFmSessionKey = batch.getLastFmSessionKey();
            Session session = Session.createSession(
                    this.lastFmCredentials.getProperty("lastfm.apikey"),
                    this.lastFmCredentials.getProperty("lastfm.secret"),
                    lastFmSessionKey);

            List<ScrobbleData> scrobbleData = (List<ScrobbleData>) Utils.transformTracksToScrobbleData(batch.getNewTracks());
            List<ScrobbleResult> result = de.umass.lastfm.Track.scrobble(scrobbleData, session);
            Utils.logFailedScrobbleAttempts(result, session.getUsername());
        }
    }
}
