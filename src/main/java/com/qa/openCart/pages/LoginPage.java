package com.qa.openCart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	int i=10;
	private WebDriver driver;
	private ElementUtil eleUtil;
	// private By locators: page objects

	private final By emailID = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@class='btn btn-primary']");
	private final By forgotPwdLink = By.linkText("Forgotten Password");
	private final By header = By.xpath("//h2[.='New Customer']");
    private final By registerLink =By.linkText("Register");
    
    private final By loginErrorMessg=By.cssSelector("div.alert.alert-danger.alert-dismissible");
    
    private static final Logger log = LogManager.getLogger(LoginPage.class);
	// public constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}

	// public page method/actions
	@Step("getting the login page title...")
	public String getLoginPageTitle() {
		String title=eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE,AppConstants.DEFAULT_SHORT_WAIT);
		//System.out.println("Login page title :" + title);
		log.info("Login page title :" + title);
		return title;
	}

	@Step("getting the login page URL...")
	public String getLoginPageURL() {
		String url=eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL,AppConstants.DEFAULT_SHORT_WAIT);
		//System.out.println("Login page URL :" + url);
		log.info("Login page URL :" + url);
		return url;
	}

	@Step("forgot password link exist...")
	public boolean isForgotPwdExist() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}

	@Step("Page header exist...")
	public boolean isheaderExist() {
		return eleUtil.isElementDisplayed(header);
	}

	@Step("login with the correct username : {0} and password : {1}")
	public AccountsPage doLogin(String appUsername, String appPassword){
		//System.out.println("Application credentials: " + appUsername + ":" + appPassword);
		log.info("Application credentials: " + appUsername + ":" +"************");
		
		eleUtil.waitForElementVisible(emailID,AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(appUsername);
		eleUtil.doSendKeys(password, appPassword);
		eleUtil.doClick(loginBtn);
       return new AccountsPage(driver);
	}
	
	@Step("login with the in-correct username : {0} and password : {1}")
	public boolean doLoginWithInvalidCredenrials(String invalidUN,String invalidPWD) {
		log.info("Invalid Application credentials: " + invalidUN + ":" + invalidPWD);
		WebElement emailEle=eleUtil.waitForElementVisible(emailID,AppConstants.DEFAULT_MEDIUM_WAIT);
		emailEle.clear();
		emailEle.sendKeys(invalidUN);
		
		eleUtil.doSendKeys(password, invalidPWD);
		eleUtil.doClick(loginBtn);
		String errorMessg=eleUtil.doElementGetText(loginErrorMessg);
		log.info("invalid creds error messg : "+errorMessg);
		if(errorMessg.contains(AppConstants.LOGIN_BLANK_CREDS_MESSG)) {
			log.info("======Blank credential warning text========"+AppConstants.LOGIN_BLANK_CREDS_MESSG);
			return true;
		}
		else if (errorMessg.contains(AppConstants.LOGIN_INVALID_CREDS_MESSG)) {
			log.info("======Invalid credential warning text========"+AppConstants.LOGIN_INVALID_CREDS_MESSG);
			return true;
		} else
		return false;
	}
	
	
	@Step("Navigating to register page...")
	public RegisterPage navigateToRegisterPage() {
		log.info("trying to navigate to resister page ...");
		eleUtil.waitForElementVisible(registerLink, AppConstants.DEFAULT_SHORT_WAIT).click();
		return new RegisterPage(driver);
	}

}
