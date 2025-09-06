package com.qa.openCart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.openCart.base.BaseTest;
import com.qa.openCart.utils.ExcelUtil;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	@DataProvider
	public Object[][] getProducts() {
		return new Object[][] {{"macbook","MacBook Pro"},
			                   {"samsung","Samsung SyncMaster 941BW"},
			                   {"imac","iMac"},
			                   {"canon","Canon EOS 5D"}};
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return ExcelUtil.getTestData("Sheet4");
	}
	
	
	@Test(dataProvider="getProductTestData")
	public void productHeaderTest(String searchKey,String productName) {
		searchResultsPage=accPage.doSearch(searchKey);
		productInfoPage=searchResultsPage.selectProduct(productName);
		String actHeader=productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader,productName);
	}
	
	@DataProvider
	public Object[][] getProductImage() {
		return new Object[][] {{"macbook","MacBook Pro",4},
			                   {"samsung","Samsung SyncMaster 941BW",1},
			                   {"canon","Canon EOS 5D",3}};
	}
	
	@Test(dataProvider = "getProductImage")
	public void productImagesCountTest(String searchKey,String productName,int imgCount) {
		searchResultsPage=accPage.doSearch(searchKey);
		productInfoPage=searchResultsPage.selectProduct(productName);
		int actImagesCount=productInfoPage.getProductImages();
		Assert.assertEquals(actImagesCount,imgCount);
	}
	
	@Test
	public void productTest() {
		searchResultsPage=accPage.doSearch("macbook");
		productInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		Map<String,String> productDataMap=productInfoPage.getProductData();
		
		SoftAssert softAssert=new SoftAssert();
		
		softAssert.assertEquals(productDataMap.get("Brand"),"Apple");
		softAssert.assertEquals(productDataMap.get("Availability"),"Out Of Stock");
		softAssert.assertEquals(productDataMap.get("extaxPrice"),"$2,000.00");
		softAssert.assertEquals(productDataMap.get("Product Code"),"Product 18");
		softAssert.assertEquals(productDataMap.get("Product_Name"),"MacBook Pro");
		softAssert.assertEquals(productDataMap.get("Product_Images"),"4");
		softAssert.assertEquals(productDataMap.get("Reward Points"),"800");
		softAssert.assertEquals(productDataMap.get("productPrice"),"$2,000.00");
		softAssert.assertAll();
	}
}
