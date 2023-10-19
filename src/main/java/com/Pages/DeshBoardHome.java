package com.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.TestBase;
import com.Utility.TestUtils;

public class DeshBoardHome extends TestBase {
	// Locators collectios

	@FindBy(xpath = "//a[contains(text(),'Home')]")
	WebElement homeButton;

	@FindBy(xpath = "//input[@id='firstname']")
	WebElement firstName;

	@FindBy(xpath = "//input[@id='lastname']")
	WebElement lastName;

	@FindBy(xpath = "//input[@id='salesresult']")
	WebElement amounts;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	WebElement submitButton;

	@FindBy(xpath = "//button[contains(text(),'Show performance')]")
	WebElement showPerformance;

	@FindBy(xpath = "//*[@id='sales-results']/table")
	WebElement salseResults;

	// Initializing Page Factory

	public DeshBoardHome() {
		PageFactory.initElements(driver, this);
	}

	public String validatePageTitle() {
		String title = driver.getTitle();
		return title;
	}

	public HomePage enterSalseData(String fName, String lName, String totalSale) {
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		amounts.sendKeys(totalSale);
		submitButton.click();		
		return new HomePage();
	}

	public void displatResult() {
		List<WebElement> rowValue = salseResults.findElements(By.tagName("tr"));
		for (WebElement rr : rowValue) {
			System.out.println(rr.getText());
		}
	}

	public void bossMessages() {
		showPerformance.click();
		List<WebElement> bossMessage = salseResults.findElements(By.tagName("tr"));
		for (int i = 2; i < bossMessage.size(); i++) {
			System.out.println("Boss Message for salse performance:- " + bossMessage.get(2).getText());
			break;

		}

	}
}
