/*
 * UserRoles.java Created On: 04/15/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.authroization;

/**
 * @author Chris
 *
 */
public enum UserRoles {

    Admin("Admin"), User("User");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

}
