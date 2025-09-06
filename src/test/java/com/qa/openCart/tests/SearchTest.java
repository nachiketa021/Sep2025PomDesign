package com.qa.openCart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.openCart.base.BaseTest;

public class SearchTest extends BaseTest {
@BeforeClass
public void searchSetup() {
	accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
}

@Test
public void searchTest() {
	searchResultsPage=accPage.doSearch("macbook");
	productInfoPage=searchResultsPage.selectProduct("MacBook Pro");
	String actHeader=productInfoPage.getProductHeader();
	Assert.assertEquals(actHeader,"MacBook Pro");
}

}
