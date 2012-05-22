/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import junit.framework.Assert;
import net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.domain.Mix;
import org.joda.time.DateTime;
import org.junit.Test;


/**
 *
 * @author daniel
 */
public class UtilsTest {

    @Test
    public void testGetListenTimeForNewestMix() throws Throwable {
        Collection mixes = new ArrayList();
        Mix m1 = new Mix();
        final Date newest = new Date();
        m1.setListenTime(newest);
        Mix m2 = new Mix();
        m2.setListenTime(new DateTime().minusHours(1).toDate());

        mixes.add(m2);
        mixes.add(m1);
        mixes.add(m2);

        Assert.assertEquals(newest, Utils.getListenTimeForNewestMix(mixes));
    }
}
