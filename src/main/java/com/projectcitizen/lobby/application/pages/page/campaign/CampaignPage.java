/*
 * CampaignPage.java Created On: 10/04/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.application.pages.page.campaign;

import java.math.BigDecimal;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;
import com.projectcitizen.lobby.application.dao.CampaignDao;
import com.projectcitizen.lobby.application.pages.BasePage;
import com.projectcitizen.lobby.entities.Campaign;

/**
 * @author Chris
 *
 */
public class CampaignPage extends BasePage {

    private static final long serialVersionUID = -6311002837515184469L;

    @Inject
    private CampaignDao campaignDao;

    /**
     * @param parameters
     */
    public CampaignPage(PageParameters parameters) {
        super(parameters);

        Model<Campaign> model = Model.of(new Campaign());
        setDefaultModel(model);

        Form<Campaign> campaignForm = new Form<Campaign>("campaignForm", model) {

            private static final long serialVersionUID = 1L;

            /*
             * (non-Javadoc)
             * @see org.apache.wicket.markup.html.form.Form#onSubmit()
             */
            @Override
            protected void onSubmit() {
                super.onSubmit();
            }

        };

        createFormFields(model, campaignForm);

        add(campaignForm);
    }

    /**
     * @param model
     * @param campaignForm
     */
    private void createFormFields(Model<Campaign> model, Form<Campaign> campaignForm) {

        campaignForm.add(new NumberTextField<BigDecimal>("goal", new PropertyModel<>(model, "goal")));

        campaignForm.add(new TextField<String>("tags", new PropertyModel<>(model, "tags")));

        campaignForm.add(new TextField<String>("cause", new PropertyModel<>(model, "cause")));

        campaignForm.add(new NumberTextField<BigDecimal>("donationLevel", new PropertyModel<>(model, "donationLevel")));

        campaignForm.add(createSubmitButton("addCampaign", model));

    }

    /**
     * @param pageModel
     * @param markupId
     * @return
     */
    private Button createSubmitButton(String markupId, Model<Campaign> pageModel) {

        Button submitButton = new Button(markupId) {

            private static final long serialVersionUID = 1L;

            /*
             * (non-Javadoc)
             * @see org.apache.wicket.markup.html.form.Button#onSubmit()
             */
            @Override
            public void onSubmit() {
                super.onSubmit();

                Campaign newCampaign = pageModel.getObject();
                newCampaign.setDonationLevel(new BigDecimal("0.00"));

                campaignDao.insertUpdateCampaign(newCampaign);
            }

        };
        return submitButton;
    }
}
