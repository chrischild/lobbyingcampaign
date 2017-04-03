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

import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

/**
 *
 * @author Ron
 */
public class MenuItem extends Panel {
    private static final long serialVersionUID = 3512802589536861966L;

    public MenuItem(String id, MenuLink link, boolean isActive, String fontAwesome, Model<String> linkText) {
        super(id);

        this.setOutputMarkupId(false);
        link.setOutputMarkupId(false);

        WebMarkupContainer iMarkup = new WebMarkupContainer("i");
        iMarkup.add(new AttributeAppender("class", Model.of(fontAwesome)));
        link.add(iMarkup);

        WebMarkupContainer spanMarkup = new WebMarkupContainer("span");
        spanMarkup.add(new Label("linkText", Model.of(linkText)));
        link.add(spanMarkup);

        add(link);

        if (isActive) {
            add(new AttributeAppender("class", Model.of("active")));
        }
    }

    /**
     * @param id
     * @param link
     * @param isActive
     * @param fontAwesome
     * @param linkText
     * @param subMenuItems
     */
    public MenuItem(String id, MenuLink link, boolean isActive, String fontAwesome, Model<String> linkText,
        // TODO add fa fa-right icon when sub menu exists
        List<SubMenuItem> subMenuItems) {
        super(id);

        this.setOutputMarkupId(false);
        link.setOutputMarkupId(false);

        WebMarkupContainer iMarkup = new WebMarkupContainer("i");
        iMarkup.add(new AttributeAppender("class", Model.of(fontAwesome)));
        link.add(iMarkup);

        WebMarkupContainer spanMarkup = new WebMarkupContainer("span");
        spanMarkup.add(new Label("linkText", Model.of(linkText)));
        link.add(spanMarkup);
        add(link);

        RepeatingView subItems = new RepeatingView("subItems");
        for (SubMenuItem subItem : subMenuItems) {
            subItems.add(subItem);
        }

        WebMarkupContainer ulMarkup = new WebMarkupContainer("ul");
        if (subItems.size() != 0) {
            ulMarkup.add(new AttributeAppender("class", Model.of("treeview-menu")));
        }

        ulMarkup.add(subItems);
        add(ulMarkup);

        if (isActive) {
            add(new AttributeAppender("class", Model.of("active treeview")));
        }
    }
}
