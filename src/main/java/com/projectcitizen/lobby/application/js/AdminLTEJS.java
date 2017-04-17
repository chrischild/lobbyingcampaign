/*
 * AdminLTEJS.java Created On: 03/29/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.application.js;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * @author Chris
 *
 */
public class AdminLTEJS extends JavaScriptResourceReference {

    private static final long serialVersionUID = 4249178694644741012L;
    public static final AdminLTEJS INSTANCE = new AdminLTEJS();

    public AdminLTEJS() {
        super(AdminLTEJS.class, "AdminLTE.js");
    }
}
