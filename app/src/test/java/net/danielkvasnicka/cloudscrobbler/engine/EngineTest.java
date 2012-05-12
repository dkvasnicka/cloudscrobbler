package net.danielkvasnicka.cloudscrobbler.engine;

import com.googlecode.jeeunit.JeeunitRunner;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import javax.inject.Inject;
import net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain.MixTrack;
import net.danielkvasnicka.cloudscrobbler.engine.api.NewTracks;
import net.danielkvasnicka.cloudscrobbler.engine.api.Track;
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

    @Test
    public final void testNewTracksEventObserver() throws Throwable {

        this.engine.scrobble(new NewTracks() {

            public String getLastFmSessionKey() {
                return "4027b9835f7788c56e4292a47080e4d2";
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
}
