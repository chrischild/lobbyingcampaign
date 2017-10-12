/**
 * 
 */
package com.projectcitizen.lobby.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Chris
 *
 */
@Entity
@Table(name = "users", schema = "projectcitizen")
public class User implements Serializable {

    private static final long serialVersionUID = 6911721115889614270L;

    private long userId;
    private String username;
    private String password;
    private String name;
    private String address;
    private String email;
    private String token;
    // private String interests;
    private Boolean alerts;
    private Set<Role> roles = new HashSet<Role>(0);
    private Set<Campaign> campaigns = new HashSet<Campaign>(0);

    /**
     * @return the userId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(long userId) {
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
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

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

    /**
     * @return the roles
     */
    //FIXME change eager to lazy and figure out error
    @ElementCollection(targetClass = Role.class)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usersroles", schema = "projectcitizen", joinColumns = {
            @JoinColumn(name = "userid") }, inverseJoinColumns = { @JoinColumn(name = "roleid") })
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles
     *            the roles to set
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * @return the campaigns
     */
    //FIXME change eager to lazy and figure out error
    @ElementCollection(targetClass = Campaign.class)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "userscampaigns", schema = "projectcitizen", joinColumns = {
            @JoinColumn(name = "userid") }, inverseJoinColumns = { @JoinColumn(name = "campaignid") })
    public Set<Campaign> getCampaigns() {
        return campaigns;
    }

    /**
     * @param campaigns the campaigns to set
     */
    public void setCampaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

}
