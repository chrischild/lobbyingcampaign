/*
 * UserDaoImp.java Created On: 03/23/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.projectcitizen.dao.UserDao;
import com.projectcitizen.entities.User;
import com.projectcitizen.util.HibernateUtil;

/**
 * @author Chris
 *
 */
public class UserDaoImp implements UserDao {

    /* (non-Javadoc)
     * @see com.projectcitizen.dao.UserDao#findUser(java.lang.Integer)
     */
    @Override
    public User findUser(Integer userId) {

        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        User user = session.get(User.class, userId);
        return user;
    }

    /* (non-Javadoc)
     * @see com.projectcitizen.dao.UserDao#findUsers()
     */
    @Override
    public List<User> findUsers() {

        String hql = "FROM User";
        List<User> users = HibernateUtil.createSessionFactory().getCurrentSession().createQuery(hql).list();
        return users;
    }

    /* (non-Javadoc)
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
