/*
 * Copyright 2013 Ron.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.projectcitizen.navigationpanel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * The baseclass for both navigation forms. The base functionality of creating a list with the
 * menuitems in it.
 * 
 * @author Chris Child
 */
public abstract class NavigationPanel extends Panel {

    private static final long serialVersionUID = -5568250122412968503L;

    private List<SubMenuItem> subMenuItems = new ArrayList<SubMenuItem>();

    /**
     * Create the navigation list.
     * 
     * @param builder
     *            the initialized builder
     */
    public NavigationPanel(Builder builder, Page page) {

        super(builder.id);
        RepeatingView menuItems = new RepeatingView("menuItems");
        MenuItem menuItem;

        for (MenuLink link : builder.links) {
            boolean mainItemActive = link.getLinkPage().equals(builder.activePage.getClass());
            boolean subItemActive = false;


            for (MenuLink subLink : link.getSubMenuLink()) {
                subItemActive = subLink.getLinkPage().equals(builder.activePage.getClass());
                if (subItemActive) {
                    mainItemActive = true;
                }
                SubMenuItem subItem = new SubMenuItem(menuItems.newChildId(), subLink, subItemActive,
                    subLink.getFontAwesome(), subLink.getLinkText());
                subMenuItems.add(subItem);
            }

            menuItem = new MenuItem(menuItems.newChildId(), link, mainItemActive, link.getFontAwesome(),
                link.getLinkText(), subMenuItems);

            menuItems.add(menuItem);
            subMenuItems.clear();

        }
        add(menuItems);
    }

    public static class Builder implements Serializable {
        private static final long serialVersionUID = 2395309898068944372L;

        protected String id;
        private Page activePage;
        private PageParameters parameters;
        private List<MenuLink> links = new ArrayList<MenuLink>();


        /**
         * Create the builder that will do the actual work.
         * 
         * @param id
         *            the wicket id that will be used to add it to the page
         * @param activePage
         *            the current page. This is used to determine where to put the active class.
         * @param parameters
         */
        public Builder(String id, Page activePage, PageParameters parameters) {
            this.id = id;
            this.activePage = activePage;
            this.parameters = parameters;
        }

        /**
         * 
         * @param linkText
         * @param linkPage
         *            needs to be set to an empty implementation of WebPage to accommodate when
         *            the top menu item has sub menus
         * @param fontAwesome
         * @param hasSubMenu
         * @return
         */
        public Builder addMenuItem(Model<String> linkText, final Class<? extends Page> linkPage, String fontAwesome,
            Boolean hasSubMenu) {
            MenuLink link = new MenuLink("link", linkText, linkPage, fontAwesome, hasSubMenu, parameters);
            links.add(link);
            return this;
        }

        /**
         * 
         * @param linkText
         * @param linkPage
         * @param fontAwesome
         * @return
         */
        public Builder addSubMenuItem(Model<String> linkText, final Class<? extends Page> linkPage,
            String fontAwesome) {
            if (linkPage != null) {
                MenuLink link = new MenuLink("subLink", linkText, linkPage, fontAwesome, false, parameters);
                MenuLink menuLink = links.get(links.size() - 1);
                menuLink.getSubMenuLink().add(link);
            }
            return this;
        }
    }


}
