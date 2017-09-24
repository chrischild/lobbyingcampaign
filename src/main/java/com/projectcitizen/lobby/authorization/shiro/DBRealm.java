/*
 * SecurityService.java Created On: 04/16/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.authorization.shiro;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;

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

    private static final int HASH_ITERATIONS = 200000;
    private static final byte[] PRIVATE_SALT = CodecSupport.toBytes(
        "2TK3GQXzuXnU1qZ&*qxR%#9#%dYeAJvwSuFs8L%8kwrfmfg0Zeoav^AqbcKVeD78S7bfShIQXI8*6AFU2ZhjBC1k0g@KsKO$MH8U");

    @Inject
    UserDao userDao;
    @Inject
    RoleDao roleDao;
    @Inject
    DefaultPasswordService passwordService;

    private PasswordMatcher passwordMatcher = new PasswordMatcher();

    /**
     * Default Constructor
     */
    public DBRealm() {
        setCacheManager(new MemoryConstrainedCacheManager());

        setCredentialsMatcher(passwordMatcher);

        init();
    }

    public void createUser(User user, String role) {

        passwordService.setHashService(setupHashService());

        String encryptPassword = passwordService.encryptPassword(user.getPassword());
        user.setPassword(encryptPassword);
        user.setRoles(Collections.singleton(getRoleByName(role)));
        userDao.insertUpdateUser(user);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.shiro.realm.jdbc.JdbcRealm#doGetAuthenticationInfo(org.apache.shiro.authc.
     * AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        passwordService.setHashService(setupHashService());
        passwordMatcher.setPasswordService(passwordService);

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

        passwordService.setHashService(setupHashService());
        passwordMatcher.setPasswordService(passwordService);

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
     * 
     * @param role
     * @return role from database
     */
    private Role getRoleByName(String role) {
        return roleDao.findRoleByName(role);
    }

    /**
     * 
     * @return Configured Hash Service
     */
    private DefaultHashService setupHashService() {
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashIterations(HASH_ITERATIONS); // 200000
        hashService.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        hashService.setPrivateSalt(new SimpleByteSource(PRIVATE_SALT));
        hashService.setGeneratePublicSalt(true);

        return hashService;
    }

    /**
     * @param username
     * @return
     */
    private SimpleAccount getAccount(String username) {

        User user = userDao.findUserByUsername(username);
        SimpleAccount account = new SimpleAccount(user.getUsername(), user.getPassword(), getName());
        account.setCredentialsSalt(new SimpleByteSource(PRIVATE_SALT));

        Set<String> roles = new HashSet<String>();
        for (Role role : user.getRoles()) {
            roles.add(role.getRole());
        }

        account.setRoles(roles);
        account.addStringPermission("view");

        return account;
    }
}