/*
 * CampaignPage.java Created On: 10/04/2017
 *
 * Copyright: Project Citizen
 */
package com.projectcitizen.lobby.application.pages.page.campaign;

import java.math.BigDecimal;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.projectcitizen.lobby.application.pages.BasePage;
import com.projectcitizen.lobby.entities.Campaign;

/**
 * @author Chris
 *
 */
public class CampaignPage extends BasePage {

    private static final long serialVersionUID = -6311002837515184469L;

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

        campaignForm.add(createGoalMet("goalMet", model));
    }

    /**
     * @param markupId
     * @param model
     * @return
     */
    private RadioGroup<Boolean> createGoalMet(String markupId, Model<Campaign> model) {

        RadioGroup<Boolean> goalGroup = new RadioGroup<>(markupId, new PropertyModel<>(model, "goalMet"));

        Radio<Boolean> goalYes = new Radio<Boolean>(markupId + "Yes", Model.of(Boolean.TRUE));
        goalGroup.add(goalYes);

        Radio<Boolean> goalNo = new Radio<Boolean>(markupId + "No", Model.of(Boolean.FALSE));
        goalGroup.add(goalNo);

        return goalGroup;
    }

}
