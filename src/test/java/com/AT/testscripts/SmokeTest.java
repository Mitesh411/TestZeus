package com.AT.testscripts;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.AT.base.BaseTest;
import com.AT.base.HTTPClientWrapper;

/**
 * @author Robin
 * @date: 28/09/2021
 * @purpose: This test covers the login to UI, fetches the UI API details and
 *           prints them to console 👼
 */

public class SmokeTest extends BaseTest {

	@Test(priority = 1)
	public void LoginAndFetch() throws Exception {

		// Navigation to login page
		lightningloginpage.openHomepage(SFBaseURL);
		// Submitting user id, password and logging in
		lightningloginpage.login(SFUserId, SFPassword);
		// Navigating directly to Account app
		String accountappurl = getURL("Account");
		driver.get(accountappurl);

		Reporter.log((HTTPClientWrapper.runGetRequest("/ui-api/record-ui/0015g00000S9lfUAAR")).toString());
//The data from above API call can be observed in the Emailable report under
		// Surefire reports folder

		// Sample API call For getting the base layouts
		System.out.println(HTTPClientWrapper.runGetRequest("/sobjects/Account/describe/layouts/"));

		// Sample API call For getting the actions
		System.out.println(HTTPClientWrapper.runGetRequest("/ui-api/actions/record/5006f00001fC2rmAAC"));

	}

	@Test(priority = 2, dependsOnMethods = { "LoginAndFetch" }, groups = { "smokeTest" })
	public void Logout() {

		Reporter.log("End of script");
	}
}