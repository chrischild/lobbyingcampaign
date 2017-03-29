/*
 * SbAdmin.java Created On: 03/27/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.css;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * @author Chris
 *
 */
public class SbAdmin extends CssResourceReference {

    private static final long serialVersionUID = 3349399611654448120L;
    public static final SbAdmin INSTANCE = new SbAdmin();

    public SbAdmin() {
        super(SbAdmin.class, "sb-admin-2.css");
    }
}
