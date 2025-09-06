package com.qa.openCart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.openCart.base.BaseTest;
import com.qa.openCart.utils.CsvUtil;
import com.qa.openCart.utils.ExcelUtil;
import com.qa.openCart.utils.StringUtils;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void goToRegisterPage() {
		registerPage = loginpage.navigateToRegisterPage();

	}
	@DataProvider
	public Object[][] getRegData(){
		return new Object[][] {
			{"Rocky1","autoDriver","7874606436","Rocky@1523","yes"},
			{"jocky3","ApiTester","2879605486","jocky@1283","no"},
			{"Dakula5","Postman","7879405433","Dakula@1023","yes"}
		};
		}
	
	@DataProvider
	public Object[][] getRegSheetData(){
		return ExcelUtil.getTestData("Sheet3");
	}
	
	@DataProvider
	public Object[][] getRegCSVData(){
		return CsvUtil.csvData("register");
	}
	

	@Test(dataProvider = "getRegCSVData")
	public void registerTest(String fn,String ln,String mob,String pwd,String sub_Y_N) {
		Assert.assertTrue(registerPage.userRegister(fn,ln, StringUtils.getRandomEmail(),
				mob,pwd,sub_Y_N));

	}
}
