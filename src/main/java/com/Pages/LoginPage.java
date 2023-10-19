package com.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Factory;

import com.Base.TestBase;

public class LoginPage extends TestBase{
	//Locators collection
	@FindBy(id = "username")
 	WebElement usrName;
	
	@FindBy(id="password")
 	WebElement pas;
	
	@FindBy(xpath = "//button[contains(text(),'Log in')]")
	WebElement loginButton;
	
	@FindBy(xpath = "//span[contains(text(),'maria')]")
	WebElement displayUserName;
	
	@FindBy(xpath = "//button[@id='logout']")
	WebElement logoutButton;
	
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
	public void logOut() {
		logoutButton.click();
	}
	public String displayUserName() {
		String display = displayUserName.getText();
		return display;
	}
	

}
