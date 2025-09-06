package com.qa.openCart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.openCart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest{
	
	@DataProvider
	public Object[][] getNegativeLoginData() {
		return new Object[][] {{"test234@gmail.com","Kilki@2334"},
			                   {"march2024@open.com","gilik@4545"},
			                   {"march2024@@open.com","plilkto@154"},
		                       {"","sagar@124"},
		                       {"",""}
		                       };
	}

	@Test(dataProvider = "getNegativeLoginData")
	public void negativeloginTest(String invalidUN,String invalidPWD) {
	Assert.assertTrue(loginpage.doLoginWithInvalidCredenrials(invalidUN,invalidPWD));
	
	}
}
