package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.logoutPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class LoginTests {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private logoutPOM logoutPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		
	}

//	@BeforeMethod
	@BeforeTest
	public void setUp() throws Exception {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		logoutPOM =new logoutPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
//	@AfterMethod
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
//		driver.quit();
	}
//	@Test(priority = 0)
	@Test(priority = 0,dataProvider = "excel-inputs", dataProviderClass = LoginDataProviders.class)
	public void validLoginTest(String UserName, String Password) {
		loginPOM.sendUserName(UserName);
		loginPOM.sendPassword(Password);
		loginPOM.clickLoginBtn(); 
		logoutPOM.clickOnDropdown();
//		screenShot.captureScreenShot("First");
	}
	@Test(priority = 1,enabled = false)
	public void logout() {
		logoutPOM.clickOnDropdown();
	}
}
