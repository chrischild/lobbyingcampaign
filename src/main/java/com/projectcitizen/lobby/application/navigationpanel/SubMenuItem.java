/*
 * SubMenuItem.java Created On: 03/31/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.application.navigationpanel;

/**
 * @author Chris
 *
 */
public class SubMenuItem extends MenuItem {

    private static final long serialVersionUID = -4581665618390213005L;
    
    /**
     * @param id
     * @param subLink
     * @param fontAwesome
     * @param linkText
     */
    public SubMenuItem(String id, MenuLink subLink, Boolean isActive, String fontAwesome, String linkText) {
            super(id, subLink, isActive, fontAwesome, linkText);
    }

}
