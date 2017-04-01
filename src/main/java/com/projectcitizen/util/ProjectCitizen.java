/*
 * ProjectCitizen.java Created On: 03/30/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.util;

import org.apache.wicket.Component;
import org.apache.wicket.model.StringResourceModel;

/**
 * @author Chris
 *
 */
public class ProjectCitizen {
    
    /**
     * @param string
     * @param navigationPanel
     */
    public static void getResource(String resourceKey, Component component) {
        StringResourceModel model = new StringResourceModel(resourceKey, component, null);
    };

}