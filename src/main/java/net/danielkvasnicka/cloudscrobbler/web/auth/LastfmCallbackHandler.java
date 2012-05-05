/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.web.auth;

import de.umass.lastfm.Authenticator;
import de.umass.lastfm.Session;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daniel
 */
@WebServlet(urlPatterns = { "/auth/cb" })
public class LastfmCallbackHandler extends HttpServlet {
    
    public static final String LAST_FM_SESSION_ATTR = "lastFmSession";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = Authenticator.getSession(req.getParameter("token"), 
                                  "3114f8efd48db0275c92b6bf0ac55ee5", 
                                  "0a4a58dd8cfcaa90eb51fb42a7ca1970");
        
        req.getSession().setAttribute(LAST_FM_SESSION_ATTR, session);
        resp.sendRedirect("../index.htm");
    }
}
