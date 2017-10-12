/*
 * CampaignDao.java Created On: 10/11/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.application.dao;

import com.google.inject.ImplementedBy;
import com.projectcitizen.lobby.application.dao.impl.CampaignDaoImpl;
import com.projectcitizen.lobby.entities.Campaign;

/**
 * @author Chris
 *
 */
@ImplementedBy(CampaignDaoImpl.class)
public interface CampaignDao {

    /**
     * @param newCampaign
     * @return
     */
    Campaign insertUpdateCampaign(Campaign newCampaign);

}
