/*
 * RoleDaoImpl.java Created On: 04/16/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.entities.dao.impl;

import java.util.ArrayList;
import java.util.List;

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
    public List<Role> getAllRoles() {

        List<Role> roles = new ArrayList<Role>();
        
        String hql = "FROM Role";
        roles = HibernateUtil.createSessionFactory().getCurrentSession().createQuery(hql).list();
        
        return roles;
    }

}
