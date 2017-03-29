/**
 * 
 */
package com.projectcitizen.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Chris
 *
 */
@Entity
@Table(name = "user", schema="projectcitizen")
public class User implements Serializable {

    private static final long serialVersionUID = 4999163629605477272L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;
    private String username;
    private String password;
    private String salt;
    private String name;
    private String address;
    private String email;
    // private String interests;
    private Boolean alerts;

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the username
     */
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt
     *            the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return the name
     */
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the email
     */
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    // /**
    // * @return the interests
    // */
    //
    // public String getInterests() {
    // return interests;
    // }
    //
    // /**
    // * @param interests
    // * the interests to set
    // */
    // public void setInterests(String interests) {
    // this.interests = interests;
    // }

    /**
     * @return the alerts
     */
    @Column(name = "alerts")
    public Boolean isAlerts() {
        return alerts;
    }

    /**
     * @param alerts
     *            the alerts to set
     */
    public void setAlerts(Boolean alerts) {
        this.alerts = alerts;
    }

}
