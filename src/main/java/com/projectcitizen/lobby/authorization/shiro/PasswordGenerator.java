/*
 * PasswordGenerator.java Created On: 04/16/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.authorization.shiro;

import org.apache.shiro.authc.credential.DefaultPasswordService;

import com.google.inject.servlet.RequestScoped;

/**
 * @author Chris
 *
 */
@RequestScoped
public class PasswordGenerator {

    @RequestScoped
    public DefaultPasswordService getDefaultPasswordService() {
        return new DefaultPasswordService();
    }
}
