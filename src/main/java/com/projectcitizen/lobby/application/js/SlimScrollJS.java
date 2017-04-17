/*
 * SlimScrollJS.java Created On: 03/30/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.application.js;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * @author Chris
 *
 */
public class SlimScrollJS extends JavaScriptResourceReference {

    private static final long serialVersionUID = 1816666731316493784L;
    public static final SlimScrollJS INSTANCE = new SlimScrollJS();

    public SlimScrollJS() {
        super(SlimScrollJS.class, "jquery.slimscroll.js");
    }
}
