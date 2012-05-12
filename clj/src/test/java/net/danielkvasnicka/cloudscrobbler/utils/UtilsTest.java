/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.utils;

import de.umass.lastfm.Result;
import de.umass.lastfm.scrobble.ScrobbleResult;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 *
 * @author daniel
 */
public class UtilsTest {

    @Test
    public void testLogFailedScrobbleAttempts() throws Throwable {
        Collection results = new ArrayList();
        ScrobbleResult mock = mock(ScrobbleResult.class);
        when(mock.getStatus()).thenReturn(Result.Status.FAILED);
        when(mock.getErrorMessage()).thenReturn("Some error...");
        when(mock.getArtist()).thenReturn("Astral Projection");
        when(mock.getTrack()).thenReturn("Mahadeva");
        results.add(mock);

        Utils.logFailedScrobbleAttempts(results, "testUsername");
    }
}
