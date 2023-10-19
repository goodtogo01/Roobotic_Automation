package com.Pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Factory;

import com.Base.TestBase;
import com.Utility.TestUtils;

public class LoginPage extends TestBase{
	//Locators collection
	@FindBy(id = "username")
 	WebElement usrName;
	
	@FindBy(id="password")
 	WebElement pas;
	
	@FindBy(xpath = "//button[contains(text(),'Log in')]")
	WebElement loginButton;
	
	@FindBy(xpath = "//div[contains(text(),'Invalid username or password.')]")
	WebElement inValidMessage;
	
	@FindBy(xpath = "//span[contains(text(),'maria')]")
	WebElement displayUserName;
	
	@FindBy(xpath = "//button[@id='logout']")
	WebElement logoutButton;
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]")
	WebElement logoutValieation;
	
	//Page Factory initializations
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String validatePageTitle() {
		String title = driver.getTitle();
		return title;
	}
	
	public  HomePage enterCredentials(String user, String pass) {
		usrName.sendKeys(user);
		pas.sendKeys(pass);
		loginButton.click();
		return new HomePage();
	 }
	public void logOut() throws IOException {
		logoutButton.click();
	 		if(logoutValieation.isDisplayed()) {
			System.out.println("Log out has successful..");
			TestUtils.implicitelyWaitTime();
			TestUtils.takeScreenShootSpecificElement(logoutValieation);
			
		}
	}
	public String displayUserName() {
		String display = displayUserName.getText();
		return display;
	}
	public boolean invalidEntry() {
		boolean invalidMsg = inValidMessage.isDisplayed();
	System.out.println("Error message found as : "+inValidMessage.getText());
		return invalidMsg;
	}
	

}
