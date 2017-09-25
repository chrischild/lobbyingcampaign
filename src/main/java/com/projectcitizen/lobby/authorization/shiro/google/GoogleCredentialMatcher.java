/*
 * GoogleCredentialMatcher.java Created On: 09/24/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.authorization.shiro.google;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * @author Chris
 *
 */
public class GoogleCredentialMatcher implements CredentialsMatcher {

    /*
     * (non-Javadoc)
     * @see
     * org.apache.shiro.authc.credential.CredentialsMatcher#doCredentialsMatch(org.apache.shiro.
     * authc.AuthenticationToken, org.apache.shiro.authc.AuthenticationInfo)
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        if (info instanceof GoogleAuthenticationInfo) {
            return true;
        }
        return false;
    }
}
