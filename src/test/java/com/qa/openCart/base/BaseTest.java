package com.qa.openCart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.openCart.factory.DriverFactory;
import com.qa.openCart.listeners.TestAllureListener;
import com.qa.openCart.pages.AccountsPage;
import com.qa.openCart.pages.CommonsPage;
import com.qa.openCart.pages.LoginPage;
import com.qa.openCart.pages.ProductInfoPage;
import com.qa.openCart.pages.RegisterPage;
import com.qa.openCart.pages.SearchResultsPage;

import io.qameta.allure.Description;

//@Listeners(ChainTestListener.class)
//@Listeners({ChainTestListener.class, TestAllureListener.class})
public class BaseTest {
	protected WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	protected LoginPage loginpage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
    protected RegisterPage registerPage;
    protected CommonsPage commonsPage;
    
   @Description("Launch the browser : {0} and url")
    @Parameters({"browser"})
	@BeforeTest
	public void setUp(@Optional("chrome") String browserName) {
    	
		df = new DriverFactory();
		prop=df.initProp();
		if(browserName !=null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		loginpage = new LoginPage(driver);
		commonsPage=new CommonsPage(driver);
	}
    
    @AfterMethod //will be running after each @test method
    public void attachScreenshot(ITestResult result) {
    	if(!result.isSuccess()) { //only for failure Test cases -- true
    		ChainTestListener.embed(DriverFactory.getScreenshotFile(),"image/png");
    	}
    	//ChainTestListener.embed(DriverFactory.getScreenshotFile(),"image/png");
    }

    @Description("Closing the browser after 4 seconds ...")
	@AfterTest
	public void tearDown() {
		try {
			Thread.sleep(4000);
		driver.quit();
		}catch(Exception e) {
			
		}
	}
}
