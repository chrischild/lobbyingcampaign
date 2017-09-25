/*
 * GoogleToken.java Created On: 09/24/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.authorization.shiro.google;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Chris
 *
 */
public class GoogleToken implements AuthenticationToken {

    private static final long serialVersionUID = -7116460392862969247L;
    private String id;

    public GoogleToken(String id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
     */
    @Override
    public Object getPrincipal() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()
     */
    @Override
    public Object getCredentials() {
        return null;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
