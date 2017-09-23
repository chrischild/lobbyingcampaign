/*
 * AuthController.java Created On: 04/16/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.authorization.shiro;

import java.util.Set;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.projectcitizen.lobby.authorization.DBRealm;
import com.projectcitizen.lobby.entities.Role;

/**
 * @author Chris
 *
 */
@RequestScoped
public class AuthController {

    @Inject
    private DBRealm secruityService;

    public boolean authenticate(String username, String password) {
        return false;
        // return securityService.authenticate(username, password.toCharArray());
    }

    public Roles getRoles(String username) {
//        User user = secruityService.findUserByUsername(username);
        return null;
    }

    public Roles convertRoles(Set<Role> userRoles) {
        Roles roles = new Roles();
        for (Role role : userRoles) {
            roles.add(role.getRole());
        }
        return roles;
    }

}
