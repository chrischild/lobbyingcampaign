package com.projectcitizen.lobby.application;

import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.PackageResourceGuard;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.shiro.annotation.AnnotationsShiroAuthorizationStrategy;
import org.wicketstuff.shiro.authz.ShiroUnauthorizedComponentListener;

import com.google.inject.Injector;
import com.projectcitizen.lobby.application.pages.page.HomePage;
import com.projectcitizen.lobby.application.pages.page.login.LoginPage;
import com.projectcitizen.lobby.application.pages.page.unauthorized.UnauthorizedPage;
import com.projectcitizen.lobby.application.pages.page.user.UserPage;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 * 
 * @see com.projectcitizen.Start#main(String[])
 */
public class WicketApplication extends WebApplication {

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();

        getMarkupSettings().setStripWicketTags(Boolean.TRUE);

        AnnotationsShiroAuthorizationStrategy authz = new AnnotationsShiroAuthorizationStrategy();
        getSecuritySettings().setAuthorizationStrategy(authz);
        getSecuritySettings().setUnauthorizedComponentInstantiationListener(
            new ShiroUnauthorizedComponentListener(LoginPage.class, UnauthorizedPage.class, authz));
        get().getResourceSettings().setPackageResourceGuard(new PackageResourceGuard());

        Injector bootstrapInjector = (Injector) this.getServletContext().getAttribute(Injector.class.getName());
        getComponentInstantiationListeners().add(new GuiceComponentInjector(this, bootstrapInjector));

        configureMountablePages();
        configureBootstrap();
    }

    /**
     * configure pages
     */
    private void configureMountablePages() {
        mountPage("/user", UserPage.class);
        mountPage("/login", LoginPage.class);
    }

    /**
     * /** configures wicket-bootstrap and installs the settings.
     */
    private void configureBootstrap() {

        final BootstrapSettings settings = new BootstrapSettings();
        settings.setJsResourceFilterName("footer-container");
        Bootstrap.install(this, settings);
    }

}

