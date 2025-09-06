package com.qa.openCart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.openCart.base.BaseTest;
import com.qa.openCart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	// BeforeTest --> BeforeClass
	@BeforeClass
	public void accPageSetup(){
		accPage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

	@Test
	public void islogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void accPageHeaderTest() {
		List<String> actHeadersList=accPage.getAccPageHeaders();
		Assert.assertEquals(actHeadersList.size(),AppConstants.ACC_PAGE_HEADERS_COUNT);
		Assert.assertEquals(actHeadersList,AppConstants.expectedAccPageHeadersList);
	}
}
