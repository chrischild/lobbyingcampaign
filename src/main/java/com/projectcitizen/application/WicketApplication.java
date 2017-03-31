package com.projectcitizen.application;

import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.PackageResourceGuard;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import com.projectcitizen.pages.page.HomePage;

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
        get().getResourceSettings().setPackageResourceGuard(new PackageResourceGuard());
        getComponentInstantiationListeners().add(new GuiceComponentInjector(this));

        configureBootstrap();
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

