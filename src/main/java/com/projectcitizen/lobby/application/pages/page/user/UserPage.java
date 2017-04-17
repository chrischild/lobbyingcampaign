/*
 * UserPage.java Created On: 03/30/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.application.pages.page.user;

import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;
import com.projectcitizen.lobby.application.pages.BasePage;
import com.projectcitizen.lobby.entities.User;
import com.projectcitizen.lobby.entities.dao.UserDao;

/**
 * @author Chris
 *
 */
public class UserPage extends BasePage {

    private static final long serialVersionUID = -5984038107446623205L;

    @Inject
    private UserDao userDao;

    public UserPage(PageParameters parameters) {
        super(parameters);

        User user = new User();
        setDefaultModel(Model.of(user));

        Form<User> form = new Form<User>("form", new CompoundPropertyModel<User>(user)) {
            private static final long serialVersionUID = 1L;

            /*
             * (non-Javadoc)
             * @see org.apache.wicket.markup.html.form.Form#onSubmit()
             */
            @Override
            protected void onSubmit() {
                super.onSubmit();
                User user = (User) getDefaultModelObject();
                user.setSalt("blah");

                userDao.insertUpdateUser(user);
            }

        };

        form.add(new TextField<String>("username", new PropertyModel<String>(user, "username"))
            .setRequired(Boolean.TRUE).setLabel(Model.of("Username")));

        form.add(new PasswordTextField("password", new PropertyModel<String>(user, "password"))
            .setRequired(Boolean.TRUE).setLabel(Model.of("Password")));

        form.add(new TextField<String>("name", new PropertyModel<String>(user, "name")).setRequired(Boolean.TRUE)
            .setLabel(Model.of("Name")));

        form.add(new TextField<String>("address", new PropertyModel<String>(user, "address")).setRequired(Boolean.TRUE)
            .setLabel(Model.of("Address")));

        form.add(new EmailTextField("email", new PropertyModel<String>(user, "email")).setRequired(Boolean.TRUE)
            .setLabel(Model.of("Email")));

        add(form);
    }
}
