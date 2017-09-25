/*
 * GoogleRealm.java Created On: 09/24/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.authorization.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.google.inject.Inject;
import com.projectcitizen.lobby.authorization.shiro.google.GoogleAuthenticationInfo;
import com.projectcitizen.lobby.authorization.shiro.google.GoogleCredentialMatcher;
import com.projectcitizen.lobby.authorization.shiro.google.GoogleToken;
import com.projectcitizen.lobby.entities.User;
import com.projectcitizen.lobby.entities.dao.UserDao;

/**
 * @author Chris
 *
 */
public class GoogleRealm extends AuthorizingRealm {

    @Inject
    private UserDao userDao;
    
    public GoogleRealm() {
        setAuthenticationTokenClass(GoogleToken.class);
        setCredentialsMatcher(new GoogleCredentialMatcher());
        
        init();
    }
    
    /*
     * (non-Javadoc)
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.
     * PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.
     * AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        GoogleToken googleToken = (GoogleToken) token;

        String username = googleToken.getId();
        if (username == null) {
            return null;
        }
        return getAccount(username);
    }

    /**
     * @param username
     * @return
     */
    private AuthenticationInfo getAccount(String username) {
        User user = userDao.findUserByUsername(username);
        GoogleAuthenticationInfo account = new GoogleAuthenticationInfo(user, getName());

        return account;
    }

}
