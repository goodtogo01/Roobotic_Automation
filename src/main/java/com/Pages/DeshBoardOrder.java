package com.Pages;

import java.awt.Robot;
import java.awt.print.Pageable;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.Base.TestBase;
import com.Utility.TestUtils;

public class DeshBoardOrder extends TestBase {
	// Locators collections Buttons
	@FindBy(xpath = "//a[contains(text(),'Order your robot!')]")
	WebElement orderButton; // Manue button

	@FindBy(xpath = "//button[contains(text(),'OK')]")
	WebElement allertButton;

	@FindBy(xpath = "//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]")
	WebElement listOfRadioButton;

	@FindBy(xpath = "//button[@id='order']")
	WebElement finalOrder;

	@FindBy(xpath = "//button[@id='order-another']")
	WebElement orderAnother;

	// Text
	@FindBy(xpath = "//h2[contains(text(),'Build and order your robot!')]")
	WebElement orderPgeTxt; // Build and order your robot!

	@FindBy(xpath = "//div[@id='receipt']")
	WebElement orderReceipt;

	@FindBy(xpath = "//div[@id='parts']")
	WebElement totalParts;

	@FindBy(xpath = "//p[@class='badge badge-success']")
	WebElement orderID;

	// Head Selection of Robot
	@FindBy(xpath = "//label[contains(text(),'1. Head:')]")
	WebElement head;
	@FindBy(xpath = "//select[@id='head']") // select class
	WebElement listOfHead;

	// Body Selection of Robot
	@FindBy(xpath = "//label[contains(text(),'2. Body:')]")
	WebElement body;

	// Legs Selection of Robot
	@FindBy(xpath = "//label[contains(text(),'3. Legs:')]")
	WebElement legs;
	@FindBy(xpath = "//input[@type='number']") // select class
	WebElement sizeOfLegs;

	// Shipping addredd
	@FindBy(xpath = "//input[@id='address']")
	WebElement shippingAddress;

	@FindBy(xpath = "//body/div[@id='root']/div[1]/div[1]/div[1]/div[2]")
	WebElement roobotImage;


	@FindBy(xpath = "//*[@id='preview']")
	WebElement previewMyOrders;
	// Order receipt

	// Page Factory initializations
	public DeshBoardOrder() {
		PageFactory.initElements(driver, this);
	}

	public void orders() {
		orderButton.click();
	}

	public String orderPageDisplay() {
		String pageText = orderPgeTxt.getText();
		return pageText;
	}

	public void orderManue() {
		orderButton.click();
		allertButton.click();
	}

	// Head Selection
	public void headSelection(int indx) {
		String heads = head.getText();
		System.out.println("We are on the section number : " + heads);
		Select se = new Select(listOfHead);
		se.selectByIndex(indx);
		System.out.println("Available Item in the list is : ");
		for (int i = 1; i < se.getOptions().size(); i++) {
			System.out.println(se.getOptions().get(i).getText());
		}
		System.err.println("\nSelected Head is : " + se.getOptions().get(indx).getText() + "\n");
	}

	// Body Section
	public void bodySelections(int bodyNum) {
		String bodys = body.getText();
		System.out.println("We are on " + bodys);
		for (int i = 1; i < listOfRadioButton.getText().length(); i++) {
			System.out.println(listOfRadioButton.getText());
			break;
		}
		// Select radio based on requirements
		String xpathHead = "//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/div[";
		String xpathTail = "]/label[1]";
		driver.findElement(By.xpath(xpathHead + bodyNum + xpathTail)).click();
		System.err.println("\nSelected Body is : "
				+ driver.findElement(By.xpath(xpathHead + bodyNum + xpathTail)).getText() + "\n");
	}

	// Leg Section
	public void legSelection(int legsCount) {
		String num = String.valueOf(legsCount);
		String numberOflegs = legs.getText();
		System.out.println("We are on : " + numberOflegs);
		sizeOfLegs.sendKeys(num);
		System.err.println("Total Lags count for this robot is : " + legsCount + "\n");
		// System.out.println("\nSelected Item from the list is : "
		// +select.getOptions().get(legsCount).getText());
	}

	public void enterStippingAddress(String address) {
		shippingAddress.sendKeys(address);
	}

	public void clicOnPreview() {
		try {
			WebElement ele = driver.findElement(By.xpath("//*[@id='preview']"));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click()", ele);
		} catch (Exception e) {
			e.getMessage();
		}
		
	}

	public boolean previiews() throws IOException {
		TestUtils.implicitelyWaitTime();
		previewMyOrders.click();
		boolean robotDiplayed = roobotImage.isDisplayed();
		TestUtils.takeScreenShootSpecificElement(roobotImage);
		return robotDiplayed;
	}

	public void orderSepcificDetails() {
		String orderId = orderID.getText();
		System.out.println("Order ID : " + orderId);
		String partsDetails = totalParts.getText();
		System.out.println("Number of Parts in the roobot : " + partsDetails);

	}

	public void fullReceiptDisplay() throws IOException {
		finalOrder.click();
		String orders = orderReceipt.getText();
		System.out.println("\nFinal Receipts of order: " + orders + "\n");
		TestUtils.takeScreenShootSpecificElement(orderReceipt);

	}

	public void backToOrderPage() {
		orderAnother.click();
		allertButton.click();
		System.out.println("Back To Order Roobot Page. ");
	}
}
