/**
 * 
 */
package com.projectcitizen.dao;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.projectcitizen.dao.impl.UserDaoImp;
import com.projectcitizen.entities.User;

/**
 * @author Chris
 *
 */
@ImplementedBy(UserDaoImp.class)
public interface UserDao {

    /**
     * @param userId
     * @return
     */
    public User findUser(Integer userId);

    /**
     * @return all users
     */
    public List<User> findUsers();

    /**
     * @param user
     */
    public User insertUpdateUser(User user);
}
