package com.qa.openCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.util.TimeUtils;

import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.utils.ElementUtil;

public class RegisterPage {
private WebDriver driver;
private ElementUtil eleUtil;
 
public RegisterPage(WebDriver driver) {
	this.driver=driver;
	eleUtil=new ElementUtil(driver);
}

private final By firstName=By.cssSelector("#input-firstname");
private final By lastName=By.cssSelector("#input-lastname");
private final By email=By.cssSelector("#input-email");
private final By telephone=By.cssSelector("#input-telephone");
private final By password=By.cssSelector("#input-password");
private final By confirmPassword=By.cssSelector("#input-confirm");

private final By subscribeYes=By.xpath("(//input[@name='newsletter'])[1]");
private final By subscriberNo=By.xpath("(//input[@name='newsletter'])[2]");

private final By agreeCheckBox=By.xpath("//input[@name='agree']");
private final By continueButton=By.xpath("//input[@type=\"submit\" and @value='Continue']");

private final By successMessg=By.cssSelector("#content h1");
private final By logoutLink=By.linkText("Logout");
private final By registerLink=By.linkText("Register");

public boolean userRegister(String firstName,String lastName,String email,String telephone,
		    String password,String subcribe) {
	eleUtil.waitForElementVisible(this.firstName,AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(firstName);
	eleUtil.doSendKeys(this.lastName,lastName);
	eleUtil.doSendKeys(this.email,email);
	eleUtil.doSendKeys(this.telephone,telephone);
	eleUtil.doSendKeys(this.password,password);
	eleUtil.doSendKeys(this.confirmPassword,password);
	
	if(subcribe.equalsIgnoreCase("yes")) {
		eleUtil.doClick(subscribeYes);
	}else {
		eleUtil.doClick(subscriberNo);
	}
	
	eleUtil.doClick(agreeCheckBox);
	eleUtil.waitForElementVisible(continueButton,AppConstants.DEFAULT_LARGE_WAIT).click();
	
	String successMesg=eleUtil.waitForElementVisible(this.successMessg,AppConstants.DEFAULT_LARGE_WAIT).getText();
	System.out.println(successMesg);
	
	if(successMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MESS)) {
		eleUtil.waitForElementVisible(logoutLink,AppConstants.DEFAULT_SHORT_WAIT).click();
		eleUtil.waitForElementVisible(registerLink,AppConstants.DEFAULT_SHORT_WAIT).click();
		return true;
	}else {
	return false;
	}
}
}
