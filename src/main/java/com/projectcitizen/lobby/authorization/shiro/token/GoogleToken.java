/*
 * GoogleToken.java Created On: 09/24/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.authorization.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Chris
 *
 */
public class GoogleToken implements AuthenticationToken {

    private static final long serialVersionUID = -7116460392862969247L;

    /* (non-Javadoc)
     * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
     */
    @Override
    public Object getPrincipal() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()
     */
    @Override
    public Object getCredentials() {
        // TODO Auto-generated method stub
        return null;
    }

}
