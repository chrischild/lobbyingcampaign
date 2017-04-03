/*
 * MenuLink.java Created On: 03/31/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.navigationpanel;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chris
 *
 */
public class MenuLink extends Link<Void> {

    private static final long serialVersionUID = -3214898407207719961L;
    private static final Logger log = LoggerFactory.getLogger(MenuLink.class);

    private Model<String> linkText;
    private Class<? extends Page> linkPage;
    private String fontAwesome;
    private Boolean hasSubMenu;
    private PageParameters parameters;
    private List<MenuLink> subMenuLink = new ArrayList<MenuLink>();

    public MenuLink(String id, Model<String> linkText, final Class<? extends Page> linkPage, String fontAwesome,
        Boolean hasSubMenu, PageParameters parameters) {
        super(id);

        this.linkText = linkText;
        this.fontAwesome = fontAwesome;
        this.hasSubMenu = hasSubMenu;
        this.parameters = parameters;
        if (linkPage != null) {
            this.linkPage = linkPage;
        }
    }

    /*
     * (non-Javadoc)
     * @see org.apache.wicket.markup.html.link.Link#onClick()
     */
    @Override
    public void onClick() {
        try {
            if (!hasSubMenu()) {
                Constructor<? extends Page> constructor = getLinkPage()
                    .getDeclaredConstructor(getParameters().getClass());
                setResponsePage(constructor.newInstance(getParameters()));
            }
        } catch (Exception e) {
            //get from property file
            String msg = "Error Loading Navigation";
            log.error(msg, e);
        }
    }

    /**
     * @return the linkText
     */
    public Model<String> getLinkText() {
        return linkText;
    }

    /**
     * @param linkText
     *            the linkText to set
     */
    public void setLinkText(Model<String> linkText) {
        this.linkText = linkText;
    }

    /**
     * @return the linkPage
     */
    public Class<? extends Page> getLinkPage() {
        return linkPage;
    }

    /**
     * @param linkPage
     *            the linkPage to set
     */
    public void setLinkPage(Class<? extends Page> linkPage) {
        this.linkPage = linkPage;
    }

    /**
     * @return the fontAwesome
     */
    public String getFontAwesome() {
        return fontAwesome;
    }

    /**
     * @param fontAwesome
     *            the fontAwesome to set
     */
    public void setFontAwesome(String fontAwesome) {
        this.fontAwesome = fontAwesome;
    }

    /**
     * @return the hasSubMenu
     */
    public Boolean hasSubMenu() {
        return hasSubMenu;
    }

    /**
     * @param hasSubMenu
     *            the hasSubMenu to set
     */
    public void setHasSubMenu(Boolean hasSubMenu) {
        this.hasSubMenu = hasSubMenu;
    }

    /**
     * @return the parameters
     */
    public PageParameters getParameters() {
        return parameters;
    }

    /**
     * @param parameters
     *            the parameters to set
     */
    public void setParameters(PageParameters parameters) {
        this.parameters = parameters;
    }

    /**
     * @return the subLink
     */
    public List<MenuLink> getSubMenuLink() {
        return subMenuLink;
    }

    /**
     * @param subLink
     *            the subLink to set
     */
    public void setSubLink(List<MenuLink> subLink) {
        this.subMenuLink = subLink;
    }

}
