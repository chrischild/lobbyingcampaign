/*
 * Campaign.java Created On: 09/26/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Chris
 *
 */
@Entity
@Table(name = "campaigns", schema = "projectcitizen")
public class Campaign implements Serializable {

    private static final long serialVersionUID = 1423412040433320982L;

    private long campaignId;
    private BigDecimal goal;
    private String tags;
    private String cause;
    private BigDecimal donationLevel;
    private boolean goalMet;

    /**
     * @return the campaignId
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    public long getCampaignId() {
        return campaignId;
    }

    /**
     * @param campaignId
     *            the campaignId to set
     */
    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * @return the goal
     */
    public BigDecimal getGoal() {
        return goal;
    }

    /**
     * @param goal
     *            the goal to set
     */
    @Column(name = "goal")
    public void setGoal(BigDecimal goal) {
        this.goal = goal;
    }

    /**
     * @return the tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags
     *            the tags to set
     */
    @Column(name = "tags")
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * @return the cause
     */
    public String getCause() {
        return cause;
    }

    /**
     * @param cause
     *            the cause to set
     */
    @Column(name = "cause")
    public void setCause(String cause) {
        this.cause = cause;
    }

    /**
     * @return the currentDonations
     */
    public BigDecimal getDonationLevel() {
        return donationLevel;
    }

    /**
     * @param currentDonations
     *            the currentDonations to set
     */
    @Column(name = "donation_level")
    public void setDonationLevel(BigDecimal currentDonations) {
        this.donationLevel = currentDonations;
    }

    /**
     * @return is the goalMet
     */
    public boolean isGoalMet() {
        return goalMet;
    }

    /**
     * @param goalMet
     *            set goalMet
     */
    @Transient
    public void setGoalMet(boolean goalMet) {
        this.goalMet = goalMet;
    }
}
