package net.danielkvasnicka.cloudscrobbler.engine;

import com.googlecode.jeeunit.JeeunitRunner;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import javax.inject.Inject;
import net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain.MixTrack;
import net.danielkvasnicka.cloudscrobbler.engine.api.NewTracks;
import net.danielkvasnicka.cloudscrobbler.engine.api.Track;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.domain.Listener;
import net.danielkvasnicka.cloudscrobbler.listenermanagement.repository.api.ListenerRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author daniel
 */
@RunWith(JeeunitRunner.class)
public class EngineTest {

    @Inject
    private Engine engine;

    @Inject
    private ListenerRepository listenerRepository;

    @Test
    public final void testNewTracksEventObserver() throws Throwable {
        final Listener l = this.listenerRepository.findListener("smilelover");

        this.engine.scrobble(new NewTracks() {

            public String getLastFmSessionKey() {
                return l.getLastFmSessionKey();
            }

            public Collection<Track> getNewTracks() {
                MixTrack t = new MixTrack();
                t.setArtistInfo(new HashMap() {{
                    put("name", "Astral Projection");
                }});
                
                t.setName("Mahadeva");

                return Arrays.asList(new Track[] { t });
            }
        });

    }

    @After
    public void removeScrobble() {
        
    }
}
