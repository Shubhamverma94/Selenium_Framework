package com.training.pom;
import org.openqa.selenium.WebDriver;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.Set;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KarTourPOM {
	private WebDriver driver; 
	
	public KarTourPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	                
	@FindBy(xpath="(//*[@class='item_text'])[2]")
	private WebElement ExpBtn; 
	
	
	@FindBy(xpath="(//*[@href='/southern-karnataka-circuit/'])[1]")
	private WebElement southernKarnataka;

	@FindBy(xpath="(//*[@href='/skc-destinations/'])[1]")
	private WebElement destination;
	
	@FindBy(xpath="(//*[@itemprop='url'])[4]")
	private WebElement HomeBtnOnSKC; 
	
	@FindBy(xpath="//*[@class='mkdf-page-title entry-title']")
	private WebElement FoodOverlayText; 
	
	@FindBy(xpath="(//*[@href='/skc-food/'])[1]")
	private WebElement FoodNav; 
	
	@FindBy(xpath="(//*[@href='/skc-accommodation/'])[1]")
	private WebElement Accommodation; 
	
	@FindBy(xpath="//*[@href='/skc-acc-mysuru/']")
	private WebElement MysuruCity; 
	
	@FindBy(id="nav-menu-item-128966")
	private WebElement AdoptAMonument; 
	
	@FindBy(id="mon-btn-id")
	private WebElement pdfBtn; 
	
	@FindBy(xpath="//*[@href='https://www.globalsinc.com/']")
	private WebElement GlobalLink;
	
	@FindBy(xpath="(//*[@class='download-btn-new'])[7]")
	private WebElement ticketBooking;
	
	@FindBy(xpath="(//*[@href='https://www.mysuruzoo.info/book-ticket'])[1]")
	private WebElement MysuruZoo;
	
	
	
	public void clickExpBtn() {
		this.ExpBtn.click();
	}
	
	 public void testDropdownOnHover() {
		   Actions action = new Actions(driver);
			action.moveToElement(southernKarnataka).perform();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class=' current '])[1]")));
			Assert.assertTrue(dropdown.isDisplayed(), "Dropdown is not displayed");
			driver.navigate().refresh();
    }
	 public void testHoverAndClickOnModules() throws InterruptedException {
		 Thread.sleep(5000);
			Actions action = new Actions(driver);
			action.moveToElement(southernKarnataka).perform();
			destination.click();
			String url = driver.getCurrentUrl();
			Assert.assertTrue(url.contains("/skc-destinations/"), "Failed to navigate to SKC Destinations page");
			driver.navigate().back();
		}
	 public void ClickOnModules() {
			Actions action = new Actions(driver);
			action.moveToElement(southernKarnataka).perform();
			southernKarnataka.click();
			String url = driver.getCurrentUrl();
			Assert.assertTrue(url.contains("/southern-karnataka-circuit/"), "Failed to navigate to SKC page");
		
		}
	 public void findHomeBtn() {
		 Assert.assertTrue(HomeBtnOnSKC.isDisplayed(), "Home button is not displayed");

		}
	
	 public void checkOverlayText() {
			Actions action = new Actions(driver);
			action.moveToElement(southernKarnataka).perform();
			FoodNav.click();
			String url = driver.getCurrentUrl();
			Assert.assertTrue(url.contains("/skc-food/"), "Failed to navigate to FooD page");
			Assert.assertEquals(FoodOverlayText.getText(), "Food");
		
		}
	 public void accommodationDetails() {
			Actions action = new Actions(driver);
			action.moveToElement(southernKarnataka).perform();
			Accommodation.click();
			String url = driver.getCurrentUrl();
			Assert.assertTrue(url.contains("/skc-accommodation/"), "Failed to navigate to Accommodation page");
			
			MysuruCity.click();
			List<WebElement> elements = driver.findElements(By.xpath("//*[@rel='noopener']"));
			Assert.assertTrue(elements.size() > 0, "No elements with Accommodation Details' found on the page");
		}

	 public void adoptAAmonument() {
			Actions action = new Actions(driver);
			action.moveToElement(southernKarnataka).perform();
			AdoptAMonument.click();
			String url = driver.getCurrentUrl();
			Assert.assertTrue(url.contains("/adopt-a-monument/"), "Failed to navigate to Adopt a Monument page");
			Assert.assertTrue(pdfBtn.isDisplayed());
			
		}
	 public void pdfNewWindow() {
		 String mainWindow = driver.getWindowHandle();
		    pdfBtn.click();
		    Set<String> allWindows = driver.getWindowHandles();
		    boolean newWindowFound = false;
		    for(String window : allWindows) {
		        if(!window.equals(mainWindow)) {
		            newWindowFound = true;
		            driver.switchTo().window(window);
		            Assert.assertTrue(driver.getCurrentUrl().endsWith(".pdf#toolbar=0"), "PDF document not opened in new window");
		            driver.close();
		            break;
		        }
		    }
		    Assert.assertTrue(newWindowFound, "New window not found");
		    driver.switchTo().window(mainWindow);
	 }
	 public void FooterGlobal() {
		  String mainWindow = driver.getWindowHandle();
		  GlobalLink.click();
		    Set<String> allWindows = driver.getWindowHandles();
		    boolean newWindowFound = false;
		    for(String window : allWindows) {
		        if(!window.equals(mainWindow)) {
		            newWindowFound = true;
		            driver.switchTo().window(window);
		            Assert.assertEquals(driver.getCurrentUrl(), "https://www.globalsinc.com/", "Failed to navigate to Global Inc page");
		            driver.close();
		            break;
		        }
		    }
		    Assert.assertTrue(newWindowFound, "New window not found");
		    driver.switchTo().window(mainWindow);
		    driver.navigate().back();
			
		}
	 public void TicketBookingLinks() {
		 Actions action = new Actions(driver);
			action.moveToElement(southernKarnataka).perform();
		 southernKarnataka.click();
		  ticketBooking.click();
		  String url = driver.getCurrentUrl();
			Assert.assertTrue(url.contains("/skc-ticket-booking/"), "Failed to navigate to Ticket Booking page");
			MysuruZoo.click();
			Assert.assertEquals(driver.getCurrentUrl(), "https://www.mysuruzoo.info/book-ticket");
		}
}
