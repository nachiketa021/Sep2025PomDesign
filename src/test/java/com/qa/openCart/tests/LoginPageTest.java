package com.qa.openCart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.openCart.base.BaseTest;
import com.qa.openCart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("EP-100: Design the Open Cart App Login Page")
@Feature("F-101: design open cart login feature")
@Story("US-50: devlop login core features: title,url, user is able to login")
public class LoginPageTest extends BaseTest{
	
	@Description("Login page title test.........---------========YYYYYYYYY============")
	@Owner("QA Nachiketa ")
	@Severity(SeverityLevel.MINOR)
@Test
public void loginPageTitleTest() {
	String actTitle=loginpage.getLoginPageTitle();
	ChainTestListener.log("Login page title : "+actTitle);
	Assert.assertEquals(actTitle,AppConstants.LOGIN_PAGE_TITLE);
}
	
	@Description("Forgot password link exist test...")
	@Owner("QA Nachiketa ")
	@Severity(SeverityLevel.CRITICAL)
@Test
public void loginPageURLTest() {
	String actURL=loginpage.getLoginPageURL();
	ChainTestListener.log("Login page url : "+actURL);
	Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
}
	
	@Description("Login page title test...")
	@Owner("QA Nachiketa ")
	@Severity(SeverityLevel.MINOR)
@Test
public void isForgotPwdExistTest() {
	Assert.assertTrue(loginpage.isForgotPwdExist());
}

	@Description("Login page header test...")
	@Owner("QA Nachiketa ")
	@Severity(SeverityLevel.MINOR)
@Test
public void isLoginPageHeaderExistTest() {
	Assert.assertTrue(loginpage.isheaderExist());
}

	@Description("user is able to login to app with the correct credentials...")
	@Owner("QA Nachiketa ")
	@Severity(SeverityLevel.BLOCKER)
@Test(priority=Integer.MAX_VALUE)
public void loginTest()  {
	accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	
	Assert.assertTrue(accPage.isLogoutLinkExist());
}

	
	
	
	
}
