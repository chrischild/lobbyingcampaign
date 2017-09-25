/*
 * GoogleAuthenticationInfo.java Created On: 09/24/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.authorization.shiro.google;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import com.projectcitizen.lobby.entities.User;

/**
 * @author Chris
 *
 */
public class GoogleAuthenticationInfo implements AuthenticationInfo {

    private static final long serialVersionUID = -8024364176635557896L;
    private PrincipalCollection principalCollection;

    public GoogleAuthenticationInfo(User user, String realmName) {
        Collection<String> principals = new ArrayList<String>();
        principals.add(user.getUsername());
        this.principalCollection = new SimplePrincipalCollection(principals, realmName);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.shiro.authc.AuthenticationInfo#getPrincipals()
     */
    @Override
    public PrincipalCollection getPrincipals() {
        return principalCollection;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.shiro.authc.AuthenticationInfo#getCredentials()
     */
    @Override
    public Object getCredentials() {
        return null;
    }

}
