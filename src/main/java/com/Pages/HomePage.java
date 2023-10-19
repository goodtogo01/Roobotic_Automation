package com.Pages;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.TestBase;

public class HomePage extends TestBase {
	// Locators collection
	@FindBy(xpath = "//body/div[@id='root']/div[1]/div[1]/div[1]/div[2]/div[1]")
	WebElement homepage_txt;

	@FindBy(xpath = "//a[contains(text(),'Home')]")
	WebElement home_Button;

	@FindBy(xpath = "//a[contains(text(),'Order your robot!')]")
	WebElement yourOrder_Button;

	@FindBy(xpath = "//button[contains(text(),'Log in')]")
	WebElement login_Button;

	// Page Factory initializations
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String pageTitle() {
		String title = driver.getTitle();
		return title;

	}

	public String homePageText() {
		String text = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[2]/div[1]"))
				.getText();
		return text;
	}

	public boolean homeButton() {
		boolean btnHome = home_Button.isDisplayed();
		return btnHome;
	}

	public boolean orderButton() {
		boolean btnOrder = yourOrder_Button.isDisplayed();
		return btnOrder;
	}
	
	public boolean loginButton() {
		boolean btnLogin = login_Button.isDisplayed();
		return btnLogin;
	}
}
