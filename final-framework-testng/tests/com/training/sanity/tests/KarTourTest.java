package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.KarTourPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class KarTourTest {

	private WebDriver driver;
	private String baseUrl;
	private KarTourPOM karTourPOM;
	private static Properties properties;
//	private ScreenShot screenShot;


	@BeforeTest
	public void setUp() throws Exception {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		karTourPOM = new KarTourPOM(driver);
		baseUrl = properties.getProperty("baseURL");
//		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		  // Dismiss cookie notice if present
	    try {
	        WebElement cookieNotice = driver.findElement(By.cssSelector(".cookie-notice-container"));
	        WebElement closeButton = cookieNotice.findElement(By.id("cn-accept-cookie"));
	        closeButton.click();
	    } catch (NoSuchElementException e) {
	        // Cookie notice not found, continue
	    	//cn-accept-cookie
	    }
	}
	

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

//	@BeforeMethod
//	public void comeToMain() {
//		driver.get(baseUrl);
//	}


	@Test(priority = 0)
	public void dropdown() {
		karTourPOM.testDropdownOnHover();
		System.out.println("KT-54--->Dropdown Fonud on Hover over Southern Karnataka Circuit");
	}
	
	@Test(priority = 1)
	public void destinationRedirect() throws InterruptedException {
		karTourPOM.testHoverAndClickOnModules();
		System.out.println("KT-57--->Destination Module Found on Hover , Clicked , Redirected to page");
	}
	
	@Test(priority = 2)
	public void SKC_redirect() {
		karTourPOM.ClickOnModules();
		System.out.println("KT-59--->Southern Karnataka Circuit Clicked in Navbar, Redirected to page");
	}
	@Test(priority = 3)
	public void homeBtnPresent() {
		karTourPOM.findHomeBtn();
		System.out.println("KT-61--->Home Button found on Southern Karnataka Circuit page");
	}
	
	@Test(priority = 4)
	public void VerifyFoodOverlayText() {
		karTourPOM.checkOverlayText();
		System.out.println("KT-63--->Food Text Found on Food background Image as overlay text ");
	}
	@Test(priority = 5)
	public void AccommodationDetails() {
		karTourPOM.accommodationDetails();
		System.out.println("KT-64--->Accommodation Details Found on Mysuru City page of Accommodation");
	}
	@Test(priority = 6)
	public void AdoptAMonumentPDF() {
		karTourPOM.adoptAAmonument();
		System.out.println("KT-65--->PDF button Found on Adopt A Monument Page");
	}

	@Test(priority = 7)
	public void OpenPDFinNewWindow() {
		karTourPOM.pdfNewWindow();
		 System.out.println("KT-66--->Validate PDF opening in new window of Adopt A Monument");  
	 }

	@Test(priority = 8)
	public void GlobalLinkValidate() {
		karTourPOM.FooterGlobal();
		 System.out.println("KT-67--->Validate Global link opening in new window of Footer");
		 
	 }
	@Test(priority =9)
	public void MySuruZooTicketBooking() {
		karTourPOM.TicketBookingLinks();
		 System.out.println("KT-68--->Validate Mysuru Zoo Ticket Booking Link");  
	 }
}
