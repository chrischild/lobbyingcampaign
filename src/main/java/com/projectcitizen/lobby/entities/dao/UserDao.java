/**
 * 
 */
package com.projectcitizen.lobby.entities.dao;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.projectcitizen.lobby.entities.User;
import com.projectcitizen.lobby.entities.dao.impl.UserDaoImp;

/**
 * @author Chris
 *
 */
@ImplementedBy(UserDaoImp.class)
public interface UserDao {

    /**
     * @param userId
     * @return User
     */
    public User findUser(Integer userId);

    /**
     * @param username
     * @return User
     */
    public User findUserByUsername(String username);

    /**
     * @return all users
     */
    public List<User> findUsers();

    /**
     * @param user
     */
    public User insertUpdateUser(User user);
}
