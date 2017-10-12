/*
 * HibernateUtil.java Created On: 03/23/2017
 * 
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Chris
 *
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static SessionFactory createSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.configure().addAnnotatedClass(com.projectcitizen.lobby.entities.User.class);
        configuration.configure().addAnnotatedClass(com.projectcitizen.lobby.entities.Role.class);
        configuration.configure().addAnnotatedClass(com.projectcitizen.lobby.entities.Campaign.class);

        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }
}
