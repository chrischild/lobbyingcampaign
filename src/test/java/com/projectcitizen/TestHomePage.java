package com.projectcitizen;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.projectcitizen.lobby.application.WicketApplication;
import com.projectcitizen.lobby.application.pages.BasePage;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(BasePage.class);

		//assert rendered page class
		tester.assertRenderedPage(BasePage.class);
	}
}
