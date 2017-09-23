/*
 * ServletContextListner.java Created On: 04/08/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.application;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.shiro.config.Ini;
import org.apache.shiro.guice.web.ShiroWebModule;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.name.Names;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.projectcitizen.lobby.authorization.DBRealm;
import com.projectcitizen.lobby.authorization.googleauth.AuthorizeCallbackServlet;
import com.projectcitizen.lobby.authorization.googleauth.AuthorizeServlet;


/**
 * @author Chris
 *
 */
public class ServletContextListener extends GuiceServletContextListener {

    private ServletContext servletContext;

    /*
     * (non-Javadoc)
     * @see com.google.inject.servlet.GuiceServletContextListener#getInjector()
     */
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(createServletModule(), new MyShiroWebModule(servletContext), ShiroWebModule.guiceFilterModule());
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContext = servletContextEvent.getServletContext();
        super.contextInitialized(servletContextEvent);
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

    private class MyShiroWebModule extends ShiroWebModule {
        MyShiroWebModule(ServletContext sc) {
            super(sc);
        }

        protected void configureShiroWeb() {
            try {
                bindRealm().toConstructor(DBRealm.class.getConstructor());
            } catch (NoSuchMethodException e) {
                addError(e);
            }

            addFilterChain("/**", AUTHC);
        }

        @Provides
        Ini loadShiroIni() {
            return Ini.fromResourcePath("classpath:shiro.ini");
        }
        
        @Override
        protected void bindSessionManager(AnnotatedBindingBuilder<SessionManager> bind) {
            bind.to(DefaultWebSessionManager.class);
            bindConstant().annotatedWith(Names.named("shiro.globalSessionTimeout")).to(630000L);
            bindConstant().annotatedWith(Names.named("shiro.sessionIdUrlRewritingEnabled")).to(false);
            bind(DefaultWebSessionManager.class);
            bind(Cookie.class).toInstance(new SimpleCookie("myCookie"));
        }
        
        /* (non-Javadoc)
         * @see org.apache.shiro.guice.web.ShiroWebModule#bindWebSecurityManager(com.google.inject.binder.AnnotatedBindingBuilder)
         */
        @Override
        protected void bindWebSecurityManager(AnnotatedBindingBuilder<? super WebSecurityManager> bind) {

            DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
            CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
            securityManager.setRememberMeManager(rememberMeManager);
            bind.toInstance(securityManager);
        }
    }
}
