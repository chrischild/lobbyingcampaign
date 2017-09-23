/*
 * Role.java Created On: 04/16/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.entities.dao;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.projectcitizen.lobby.entities.Role;
import com.projectcitizen.lobby.entities.dao.impl.RoleDaoImpl;

/**
 * @author Chris
 *
 */
@ImplementedBy(RoleDaoImpl.class)
public interface RoleDao {

    /**
     * Find Role by id
     * @param id
     */
    Role findRole(Integer id);

    /**
     * Find all Roles
     */
    List<Role> getAllRoles();
    
    /**
     * Find Role by name
     * @param role to find
     */
    Role findRoleByName(String role);
    
}
