package com.AT.base.sfData.objectDescribe;

import org.testng.annotations.Test;

import com.AT.base.BaseTest;
import com.AT.base.HTTPClientWrapper;
import com.AT.base.sfData.BaseSFData;
import com.AT.base.sfData.objectDescribe.describeLayoutPOJO.SFVFDescription;

public class GetSFObject extends BaseTest {
	static BaseSFData jsonMap = new BaseSFData();

	public static String getDescribeLayout(final String obj) {
		String endpoint = "/sobjects" + "/" + obj + "/" + "describe" + "/" + "layouts";
		// Older method to run Get API request

//		GetRequest getRequest = new GetRequest();
//		return getRequest.getResponseWithOauth(endpoint).getBody().asString();

		// Newer method to run Get Api request
		String getDescribeLayout = HTTPClientWrapper.runGetRequest(endpoint).toString();
		System.out.println("API Responmse is ------" + getDescribeLayout);
		return getDescribeLayout;
	}

// SalesForce VisualForce
	public static SFVFDescription get(String obj) {
		SFVFDescription t;
		String objData = getDescribeLayout(obj);
		t = jsonMap.getDescriptionInstance(objData, SFVFDescription.class);
		return t;
	}

	// Testing
//	public static void main(String[] args) {
	@Test
	public void apiChecker() {
		SFVFDescription lead = GetSFObject.get("Lead");
//		String res1 = lead.getLayouts().get(0).getEditLayoutSections().get(0).getHeading();
		String quickAction = lead.getLayouts().get(0).getQuickActionList().getQuickActionListItems().get(2).getLabel()
				.toString();
		System.out.println("11111" + quickAction);
		String quickActionURL = lead.getLayouts().get(0).getQuickActionList().getQuickActionListItems().get(2).getUrls()
				.getDescribe();
		System.out.println(quickAction + "---" + quickActionURL);
	}

}
