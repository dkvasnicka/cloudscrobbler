/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.web.auth;

import de.umass.lastfm.Authenticator;
import de.umass.lastfm.Session;
import java.io.IOException;
import java.util.Properties;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.solder.resourceLoader.Resource;

/**
 *
 * @author daniel
 */
@WebServlet(urlPatterns = {"/auth/cb"})
public class LastfmCallbackHandler extends HttpServlet {

    public static final String LAST_FM_SESSION_ATTR = "lastFmSession";
    
    @Inject
    @Resource("META-INF/lastfm.properties")
    Properties lastFmCredentials;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = Authenticator.getSession(req.getParameter("token"),
                this.lastFmCredentials.getProperty("lastfm.apikey"),
                this.lastFmCredentials.getProperty("lastfm.secret"));

        req.getSession().setAttribute(LAST_FM_SESSION_ATTR, session);
        resp.sendRedirect("../index.htm");
    }
}
