/*
 * LoginPage.java Created On: 04/17/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.application.pages.page.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;
import com.projectcitizen.lobby.application.pages.BasePage;
import com.projectcitizen.lobby.application.pages.page.HomePage;
import com.projectcitizen.lobby.entities.User;

/**
 * @author Chris
 *
 */
public class LoginPage extends BasePage {

    private static final long serialVersionUID = -1052503951856431187L;

    @Inject
    SecurityManager securityManager;

    public LoginPage(PageParameters parameters) {
        super(parameters);

        Model<User> model = Model.of(new User());
        setDefaultModel(model);

        Form<User> loginForm = new Form<User>("loginForm", model) {

            private static final long serialVersionUID = 1L;

            /*
             * (non-Javadoc)
             * @see org.apache.wicket.markup.html.form.Form#onSubmit()
             */
            @Override
            protected void onSubmit() {
                super.onSubmit();

                boolean authResult = login((User) getDefaultModelObject());
                if (authResult) {
                    setResponsePage(HomePage.class);
                }
            }
        };

        createFormFields(model, loginForm);

        add(loginForm);
    }

    /**
     * @param model
     * @param loginForm
     */
    private void createFormFields(Model<User> model, Form<User> loginForm) {
        loginForm.add(new TextField<String>("username", new PropertyModel<String>(model, "username")));

        loginForm.add(new PasswordTextField("password", new PropertyModel<String>(model, "password")));

        Button loginButton = new Button("login");
        loginForm.setDefaultButton(loginButton);
        loginForm.add(loginButton);
    }

    /**
     * @return
     */
    private boolean login(User user) {
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        try {
            currentUser.login(token);
            return true;
        } catch (final IncorrectCredentialsException ice) {
            error("Password is incorrect.");
        } catch (final UnknownAccountException uae) {
            error("There is no account with that username.");
        } catch (final AuthenticationException ae) {
            error("Invalid username and/or password.");
        } catch (final Exception ex) {
            error("Login failed");
        }

        return false;
    }

}
