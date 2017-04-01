/*
 * BasePage.java Created On: 03/30/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.pages;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.projectcitizen.css.AdmintLTECSS;
import com.projectcitizen.css.MetisMenu;
import com.projectcitizen.js.AdminLTEJS;
import com.projectcitizen.js.SlimScrollJS;
import com.projectcitizen.navigationpanel.NavigationPanel.Builder;
import com.projectcitizen.navigationpanel.SideNavigationPanel;
import com.projectcitizen.pages.page.DummyPage;
import com.projectcitizen.pages.page.HomePage;
import com.projectcitizen.pages.page.user.UserPage;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.ChromeFrameMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.MetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.MobileViewportMetaTag;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCssReference;

public class BasePage extends WebPage {
    private static final long serialVersionUID = -6979951561493371929L;

    public BasePage(final PageParameters parameters) {
        super(parameters);

        add(new HtmlTag("html"));

        add(new MobileViewportMetaTag("viewport"));
        add(new ChromeFrameMetaTag("chrome-frame"));
        add(new MetaTag("description", Model.of("description"), Model.of("Project Citizen")));
        add(new MetaTag("author", Model.of("author"), Model.of("Chris Child")));

        Builder builder = new Builder("navigation", getPage(), parameters);
        builder = builder.addMenuItem(Model.of("Home"), HomePage.class, "fa fa-home", false)
                         .addMenuItem(Model.of("User"), DummyPage.class, "fa fa-user", true)
                         .addSubMenuItem(Model.of("Add User"), UserPage.class, "fa fa-user-plus");

        add(new SideNavigationPanel(builder, getPage()));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        Bootstrap.renderHead(response);

        response.render(CssHeaderItem.forReference(MetisMenu.INSTANCE));
        response.render(CssHeaderItem.forReference(AdmintLTECSS.INSTANCE));
        response.render(CssHeaderItem.forReference(FontAwesomeCssReference.instance()));

        response.render(JavaScriptReferenceHeaderItem.forReference(AdminLTEJS.INSTANCE));
        response.render(JavaScriptReferenceHeaderItem.forReference(SlimScrollJS.INSTANCE));
    }
}
