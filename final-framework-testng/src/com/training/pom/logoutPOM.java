package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class logoutPOM {
	private WebDriver driver; 
	
	public logoutPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="oxd-userdropdown-tab")
	private WebElement dropdown; 
	
	@FindBy(xpath="//*[contains(text(),'Logout')]")
	private WebElement logoutBtn;
	
	
	
	public void clickOnDropdown() {
		dropdown.click();
		logoutBtn.click(); 
	}
	
	
	
//	public void clickLogoutBtn() {
//		this.logoutBtn.click(); 
//	}
}
