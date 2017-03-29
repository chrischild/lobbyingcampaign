package com.projectcitizen.pages;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.projectcitizen.css.MetisMenu;
import com.projectcitizen.css.SbAdmin;

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
        add(new MetaTag("description", Model.of("description"), Model.of("Apache Wicket & Twitter Bootstrap Demo")));
        add(new MetaTag("author", Model.of("author"), Model.of("Michael Haitz <michael.haitz@agile-coders.de>")));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        Bootstrap.renderHead(response);

        response.render(CssHeaderItem.forReference(MetisMenu.INSTANCE));
        response.render(CssHeaderItem.forReference(SbAdmin.INSTANCE));
        response.render(CssHeaderItem.forReference(FontAwesomeCssReference.instance()));
    }
}
