/*
 * UserDaoImp.java Created On: 03/23/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.application.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.projectcitizen.lobby.application.dao.UserDao;
import com.projectcitizen.lobby.entities.User;
import com.projectcitizen.lobby.util.HibernateUtil;

/**
 * @author Chris
 *
 */
public class UserDaoImp implements UserDao {

    /*
     * (non-Javadoc)
     * @see com.projectcitizen.dao.UserDao#findUser(java.lang.Integer)
     */
    @Override
    public User findUser(Integer userId) {

        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        User user = session.get(User.class, userId);
        return user;
    }

    /*
     * (non-Javadoc)
     * @see com.projectcitizen.dao.UserDao#findUserByUsername(java.lang.String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public User findUserByUsername(String username) {

        User user = null;
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        session.beginTransaction();

        String hql = "From User u where u.username = :username";
        List<User> users = session.createQuery(hql).setParameter("username", username).getResultList();
        
        if(!users.isEmpty()) {
            user = users.get(0);
        }

        session.getTransaction().commit();
        session.close();
        return user;
    }

    /*
     * (non-Javadoc)
     * @see com.projectcitizen.dao.UserDao#findUsers()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> findUsers() {

        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        session.beginTransaction();

        String hql = "FROM User";
        List<User> users = HibernateUtil.createSessionFactory().getCurrentSession().createQuery(hql).list();

        session.getTransaction().commit();
        session.close();
        return users;
    }

    /*
     * (non-Javadoc)
     * @see com.projectcitizen.dao.UserDao#insertUpdateUser(com.projectcitizen.entities.User)
     */
    @Override
    public User insertUpdateUser(User user) {
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.saveOrUpdate(user);

        session.getTransaction().commit();

        return user;
    }

}
