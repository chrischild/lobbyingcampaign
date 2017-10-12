/*
 * CampaignDaoImpl.java Created On: 10/11/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.application.dao.impl;

import org.hibernate.Session;

import com.projectcitizen.lobby.application.dao.CampaignDao;
import com.projectcitizen.lobby.entities.Campaign;
import com.projectcitizen.lobby.util.HibernateUtil;

/**
 * @author Chris
 *
 */
public class CampaignDaoImpl implements CampaignDao {

    /* (non-Javadoc)
     * @see com.projectcitizen.lobby.entities.dao.CampaignDao#insertUpdateCampaign(com.projectcitizen.lobby.entities.Campaign)
     */
    @Override
    public Campaign insertUpdateCampaign(Campaign newCampaign) {
        Session session = HibernateUtil.createSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        
        session.saveOrUpdate(newCampaign);
        
        session.getTransaction().commit();
        
        return newCampaign;
    }

}
