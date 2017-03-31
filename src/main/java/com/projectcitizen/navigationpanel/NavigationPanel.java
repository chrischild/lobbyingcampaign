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
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The baseclass for both navigation forms. The base functionality of creating a list with the
 * menuitems in it.
 * 
 * @author Chris Child
 */
public abstract class NavigationPanel extends Panel {
    private static final long serialVersionUID = -5568250122412968503L;
    private static final Logger log = LoggerFactory.getLogger(NavigationPanel.class);

    /**
     * Create the navigation list.
     * 
     * @param builder
     *            the initialized builder
     */
    public NavigationPanel(Builder builder, Page page) {

        super(builder.id);
        RepeatingView rv = new RepeatingView("menuItems");

        for (Map.Entry<String, Link<Void>> links : builder.links.entrySet()) {
            String fontAwesome = links.getKey();
            Link<Void> link = links.getValue();
            boolean isActive = false;
            isActive = builder.pages.get(links.getKey()).equals(builder.activePage.getClass());

            rv.add(new MenuItem(rv.newChildId(), link, isActive, fontAwesome, builder.linkText.get(links.getKey())));
        }
        add(rv);
    }

    public static class Builder implements Serializable {
        private static final long serialVersionUID = 2395309898068944372L;

        protected String id;
        private Page activePage;
        private PageParameters parameters;
        private Map<String, Link<Void>> links = new LinkedHashMap<String, Link<Void>>();
        private Map<String, Model<String>> linkText = new LinkedHashMap<String, Model<String>>();
        private Map<String, Class<? extends Page>> pages = new LinkedHashMap<String, Class<? extends Page>>();


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

        public Builder addMenuItem(String lnkText, final Class<? extends Page> page, String fontAwesome) {
            Link<Void> link = new Link<Void>("link") {
                private static final long serialVersionUID = 1L;

                @Override
                public void onClick() {
                    try {
                        Constructor<? extends Page> constructor = page.getDeclaredConstructor(parameters.getClass());
                        setResponsePage(constructor.newInstance(parameters));
                    } catch (Exception e) {
                        String msg = "Error Loading Navigation";
                        log.error(msg, e);
                    }
                }
            };
            linkText.put(fontAwesome, Model.of(lnkText));
            links.put(fontAwesome, link);
            pages.put(fontAwesome, page);
            return this;
        }

    }
}
