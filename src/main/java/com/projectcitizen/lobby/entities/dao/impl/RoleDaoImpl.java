/*
 * RoleDaoImpl.java Created On: 04/16/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.entities.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.projectcitizen.lobby.entities.Role;
import com.projectcitizen.lobby.entities.dao.RoleDao;
import com.projectcitizen.lobby.util.HibernateUtil;

/**
 * @author Chris
 *
 */
public class RoleDaoImpl implements RoleDao {

    /*
     * (non-Javadoc)
     * @see com.projectcitizen.lobby.entities.dao.RoleDao#findRole(java.lang.Integer)
     */
    @Override
    public Role findRole(Integer roleId) {
        return HibernateUtil.createSessionFactory().getCurrentSession().get(Role.class, roleId);
    }

    /*
     * (non-Javadoc)
     * @see com.projectcitizen.lobby.entities.dao.RoleDao#getAllRoles()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getAllRoles() {

        List<Role> roles = new ArrayList<Role>();

        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        session.beginTransaction();

        String hql = "FROM Role";
        roles = session.createQuery(hql).list();

        session.getTransaction().commit();
        session.close();

        return roles;
    }

    /*
     * (non-Javadoc)
     * @see com.projectcitizen.lobby.entities.dao.RoleDao#findRoleByName(java.lang.String)
     */
    @Override
    public Role findRoleByName(String roleType) {

        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        session.beginTransaction();

        String hql = "FROM Role r where r.role = :role";
        Role role = (Role) session.createQuery(hql).setParameter("role", roleType).getSingleResult();

        session.getTransaction().commit();
        session.close();
        return role;
    }

}
