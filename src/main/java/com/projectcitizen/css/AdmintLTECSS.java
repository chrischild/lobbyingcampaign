/*
 * AdmintLTE.java Created On: 03/29/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.css;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * @author Chris
 *
 */
public class AdmintLTECSS extends CssResourceReference {

    private static final long serialVersionUID = -4780745456928481491L;
    public static final AdmintLTECSS INSTANCE = new AdmintLTECSS();
    
    public AdmintLTECSS() {
        super(AdmintLTECSS.class, "AdminLTE.css");
    }
}
