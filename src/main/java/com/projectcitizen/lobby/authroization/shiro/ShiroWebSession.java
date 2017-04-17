package com.projectcitizen.lobby.authroization.shiro;

import java.util.LinkedList;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import com.google.inject.Inject;
import com.projectcitizen.security.roles.UserRoles;

public class ShiroWebSession extends AuthenticatedWebSession {

    private static final long serialVersionUID = 5384062322781680346L;
    private static final Roles NO_ROLES = new Roles();

    @Inject
    private AuthController authController;

    public ShiroWebSession(Request request) {
        super(request);
    }

    @Override
    public boolean authenticate(String username, String password) {


        return true;
    }

    @Override
    public Roles getRoles() {

        Subject subject;

        if (((subject = SecurityUtils.getSubject()) != null) && subject.isAuthenticated()) {

            LinkedList<String> codeList;
            String[] codes;

            codeList = new LinkedList<String>();
            for (UserRoles roleType : UserRoles.values()) {
                if (subject.hasRole(roleType.getRole())) {
                    codeList.add(roleType.getRole());
                }
            }

            codes = new String[codeList.size()];
            codeList.toArray(codes);

            return new Roles(codes);
        }

        return NO_ROLES;
    }

    @Override
    public void signOut() {

        SecurityUtils.getSubject().logout();
        super.signOut();
    }
}