/*
 * AuthorizeCallbackServlet.java Created On: 04/07/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.authorization.googleauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.projectcitizen.lobby.authorization.UserRoles;
import com.projectcitizen.lobby.entities.Role;
import com.projectcitizen.lobby.entities.User;
import com.projectcitizen.lobby.entities.dao.UserDao;

/**
 * @author Chris
 *
 */
@Singleton
@WebServlet("/gauthorizecallback")
public class AuthorizeCallbackServlet extends HttpServlet {

    private static final long serialVersionUID = 751294421018368626L;
    private static final String CLIENT_SECRET = "BmhiS6acMetmdki1TbdwozEf";
    private static final String REDIRECT_URI = "http://localhost:8080/";

    @Inject
    private UserDao userDao;

    /*
     * (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stateParameter = req.getParameter("state");
        String stateAttribute = (String) req.getSession().getAttribute("state");
        System.out.println("stateParameter: " + stateParameter + "; stateAttribute: " + stateAttribute);
        if (stateAttribute != null && !stateAttribute.equals(stateParameter)) {
            resp.setStatus(401);
            req.getSession().invalidate();
            return;
        }

        // 4. Exchange code for access token and ID token
        String accessToken = obtainAccessToken(req.getParameter("code"));

        // 5. Obtain user information from the ID token
        Map<String, Object> userDetailMap = obtainUserInformation(accessToken);
        System.out.println("User's information. name: " + userDetailMap.get("name") + " email: "
            + userDetailMap.get("email") + "; email_verified: " + userDetailMap.get("verified_email"));

        String id = (String) userDetailMap.get("id");

        User user = new User();
        user = userDao.findUserByUsername(id);

        if (user.getUsername() == null) {
            user.setName((String) userDetailMap.get("name"));
            user.setEmail((String) userDetailMap.get("email"));
            user.setUsername(id);

            Role role = new Role();
            role.setRole(UserRoles.User.getRole());
            user.setRoles(Collections.singleton(role));

            userDao.insertUpdateUser(user);
        }

        // 6. Authenticate the user
        req.getSession().setAttribute("email", userDetailMap.get("email"));
        req.getSession().setAttribute("name", userDetailMap.get("name"));
        req.getSession().setAttribute("isEmailVerified", userDetailMap.get("verified_email"));

        resp.sendRedirect(REDIRECT_URI);
    }

    private String obtainAccessToken(String codeParameter) throws IOException, ClientProtocolException {
        URI uri = null;
        try {
            uri = new URIBuilder().setScheme("https").setHost("www.googleapis.com").setPath("/oauth2/v4/token")
                .setParameter("code", codeParameter).setParameter("client_id", AuthorizeServlet.CLIENT_ID)
                .setParameter("client_secret", CLIENT_SECRET)
                .setParameter("redirect_uri", AuthorizeServlet.REDIRECT_URL)
                .setParameter("grant_type", "authorization_code").build();
        } catch (URISyntaxException e) {
            System.err.println("Error building uri. Exception message: " + e.getMessage());
        }

        HttpPost httpPost = new HttpPost(uri);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpPostResponse = httpClient.execute(httpPost);

        BufferedReader reader = new BufferedReader(new InputStreamReader(httpPostResponse.getEntity().getContent()));
        StringBuilder responseString = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseString.append(line);
        }
        System.out.println(responseString.toString());

        // Returns only the 'access_token' from responseString
        ObjectMapper jacksonObjectMapper = new ObjectMapper();
        Map<String, Object> accessMap = jacksonObjectMapper.readValue(responseString.toString(), Map.class);
        String accessToken = (String) accessMap.get("access_token");

        return accessToken;
    }

    private Map<String, Object> obtainUserInformation(String accessToken)
        throws IOException, ClientProtocolException, JsonParseException, JsonMappingException {
        URI uri = null;
        try {
            uri = new URIBuilder().setScheme("https").setHost("www.googleapis.com").setPath("/userinfo/v2/me").build();
        } catch (URISyntaxException e) {
            System.err.println("Error building uri. Exception message: " + e.getMessage());
        }

        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader("Authorization", "Bearer " + accessToken);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpPostResponse = httpClient.execute(httpGet);

        BufferedReader reader = new BufferedReader(new InputStreamReader(httpPostResponse.getEntity().getContent()));
        StringBuilder respString = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            respString.append(line);
        }
        System.out.println(respString.toString());

        ObjectMapper jacksonObjectMapper = new ObjectMapper();
        Map<String, Object> userDetailMap = jacksonObjectMapper.readValue(respString.toString(), Map.class);
        return userDetailMap;
    }
}