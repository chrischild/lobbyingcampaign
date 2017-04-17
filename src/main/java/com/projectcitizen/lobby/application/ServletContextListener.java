/*
 * ServletContextListner.java Created On: 04/08/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.application;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.projectcitizen.lobby.authroization.googleauth.AuthorizeCallbackServlet;
import com.projectcitizen.lobby.authroization.googleauth.AuthorizeServlet;


/**
 * @author Chris
 *
 */
public class ServletContextListener extends GuiceServletContextListener {

    /*
     * (non-Javadoc)
     * @see com.google.inject.servlet.GuiceServletContextListener#getInjector()
     */
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(createServletModule());
    }

    /**
     * @return
     */
    private ServletModule createServletModule() {
        return new ServletModule() {

            /*
             * (non-Javadoc)
             * @see com.google.inject.servlet.ServletModule#configureServlets()
             */
            @Override
            protected void configureServlets() {

                bind(AuthorizeServlet.class);
                bind(AuthorizeCallbackServlet.class);
                bind(WicketFilter.class).in(Singleton.class);
                bind(WebApplication.class).to(WicketApplication.class);

                serve("/gauthorize").with(AuthorizeServlet.class);
                serve("/gauthorizecallback").with(AuthorizeCallbackServlet.class);

                Map<String, String> params = new HashMap<String, String>();
                params.put(WicketFilter.FILTER_MAPPING_PARAM, "/*");
                params.put("applicationClassName", "com.projectcitizen.lobby.application.WicketApplication");

                filter("/*").through(WicketFilter.class, params);
            }

        };
    }
}
