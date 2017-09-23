/*
 * SecurityService.java Created On: 04/16/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.authorization;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.google.inject.Inject;
import com.projectcitizen.lobby.entities.Role;
import com.projectcitizen.lobby.entities.User;
import com.projectcitizen.lobby.entities.dao.RoleDao;
import com.projectcitizen.lobby.entities.dao.UserDao;

/**
 * @author Chris
 *
 */
public class DBRealm extends AuthorizingRealm {

    @Inject
    UserDao userDao;
    @Inject
    RoleDao roleDao;

    /**
     * Default Constructor
     */
    public DBRealm() {
        setCacheManager(new MemoryConstrainedCacheManager());
    }

    /*
     * (non-Javadoc)
     * @see org.apache.shiro.realm.jdbc.JdbcRealm#doGetAuthenticationInfo(org.apache.shiro.authc.
     * AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken authtoken = (UsernamePasswordToken) token;
        String username = authtoken.getUsername();

        if (username == null) {
            return null;
        }

        return getAccount(username);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.shiro.realm.jdbc.JdbcRealm#doGetAuthorizationInfo(org.apache.shiro.subject.
     * PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        @SuppressWarnings("unchecked")
        Collection<String> thisRealmPrincipals = principals.fromRealm(getName());
        if (thisRealmPrincipals == null || thisRealmPrincipals.isEmpty()) {
            return null;
        }

        // note that the return value of 'getName()' here is whatever you specify it to be in
        // jsecurity.ini
        // in this case, the jsecurity.ini file calls it 'myRealm'. All realms must have a unique
        // name.

        // Since this realm supplied these principals from the doGetAuthenticationInfo method above
        // when the
        // user logged-in and that method in this simple example has only one principal - a username
        // - we can safely
        // assume the only element in this collection is that username.
        String username = thisRealmPrincipals.iterator().next();

        return getAccount(username);
    }

    /**
     * @param username
     * @return
     */
    private SimpleAccount getAccount(String username) {

        User user = userDao.findUserByUsername(username);
        SimpleAccount account = new SimpleAccount(user.getUsername(), user.getPassword(), getName());

        Set<String> roles = new HashSet<String>();
        for (Role role : user.getRoles()) {
            roles.add(role.getRole());
        }

        account.setRoles(roles);
        account.addStringPermission("view");

        return account;
    }

}
