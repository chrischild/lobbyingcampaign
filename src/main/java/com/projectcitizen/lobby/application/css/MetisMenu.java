/*
 * MetisMenu.java Created On: 03/27/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.application.css;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * @author Chris
 *
 */
public class MetisMenu extends CssResourceReference {

    private static final long serialVersionUID = -3797201767826967899L;
    public static final MetisMenu INSTANCE = new MetisMenu();

    public MetisMenu() {
        super(MetisMenu.class, "metisMenu.min.css");
    }
}
