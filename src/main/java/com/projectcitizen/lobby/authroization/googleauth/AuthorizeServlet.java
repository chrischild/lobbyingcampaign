/*
 * AuthorizeServlet.java Created On: 04/05/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.authroization.googleauth;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URIBuilder;

/**
 * @author Chris
 *
 */
@Singleton
@WebServlet("/gauthorize")
public class AuthorizeServlet extends HttpServlet {

    private static final long serialVersionUID = 6769431932865941040L;
    public static final String CLIENT_ID = "426114423866-j0i76q3itrmte63topkdidlmd7ete56s.apps.googleusercontent.com";
    public static final String REDIRECT_URL = "http://localhost:8080/gauthorizecallback";

    /*
     * (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Create an anti-forgery state token
        String state = generateAntiForgeryToken();
        req.getSession().setAttribute("state", state);

        // 2. Send an authentication request to Google
        URI uri = null;
        try {
            uri = new URIBuilder().setScheme("https").setHost("accounts.google.com").setPath("/o/oauth2/v2/auth")
                .setParameter("access_type", "offline").setParameter("client_id", CLIENT_ID).setParameter("response_type", "code")
                .setParameter("scope", "openid email").setParameter("redirect_uri", REDIRECT_URL)
                .setParameter("state", state).build();
        } catch (URISyntaxException e) {
            System.err.println("Error building uri. Exception message: " + e.getMessage());
        }
        System.out.println(uri.toURL().toString());
        resp.sendRedirect(uri.toURL().toString());
    }

    private String generateAntiForgeryToken() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }


}
