package com.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Base.TestBase;
import com.Pages.DeshBoardHome;
import com.Pages.DeshBoardOrder;
import com.Pages.HomePage;
import com.Pages.LoginPage;
import com.Utility.TestUtils;

public class DeshBoardOrderTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	DeshBoardHome deshBoardHome;
	DeshBoardOrder deshBoardOrder;
	TestUtils testUtils;
	static String sheetName = "Sheet2";

	@BeforeMethod(groups = "Initializations")
	public void tearUp() {
		initialization();

		deshBoardHome = new DeshBoardHome();
		testUtils = new TestUtils();
		homePage = new HomePage();
		loginPage = new LoginPage();
		deshBoardOrder = new DeshBoardOrder();
		loginPage.enterCredentials(prop.getProperty("userName"), prop.getProperty("password"));
		deshBoardOrder.orderManue();

	}

	@Test(groups = "Order Page Functions", priority = 1)
	public void pageTitleTest() {
		String ti = deshBoardHome.validatePageTitle();
		Assert.assertEquals(ti, "RobotSpareBin Industries Inc. - Intranet", "Title is missing!!");
		System.out.println("Page Title is : " + ti);
	}

	@Test(groups = "Order Page Functions", priority = 2, enabled = true)
	public void orderManueTest() {
		// deshBoardOrder.orderManue();
		//TestUtils.implicitelyWaitTime();
		String txt = deshBoardOrder.orderPageDisplay();
		Assert.assertEquals(txt, "Build and order your robot!", "Expected Text is missing!!");
		System.out.println("Text Found as : " + txt+"\n");
	}

	@Test(groups = "Order Page Functions", priority = 3)
	public void heasdSelectionTest() {
		deshBoardOrder.headSelection(2); // Enter Index number
	}

	@Test(groups = "Order Page Functions", priority = 4)
	public void bodySelectionTest() {
		deshBoardOrder.bodySelections(2);
	}

	@Test(groups = "Order Page Functions", priority = 5)
	public void legsSelectionTest() {
		deshBoardOrder.legSelection(2);
	}

	@DataProvider
	public Object[][] getNewData() {
		Object[][] data = TestUtils.getTestData(sheetName);
		return data;
	}

	@Test(groups = "Order Page Functions", dataProvider = "getNewData", priority = 6)
	public void shippingAddress(String address) throws IOException {
		heasdSelectionTest();
		bodySelectionTest();
		legsSelectionTest();
		deshBoardOrder.enterStippingAddress(address);
		boolean previiewOrders = deshBoardOrder.previiews();
		Assert.assertEquals(previiewOrders, true, "Robot Not created!!");
		System.out.println("Robot is created : " + previiewOrders);
	//	deshBoardOrder.orders(); // manue button
		deshBoardOrder.fullReceiptDisplay();
		TestUtils.takeScreenShoot();
		deshBoardOrder.backToOrderPage();
		orderManueTest();
	}

	@Test(groups = "Order Page Functions", priority = 7, dependsOnMethods = "shippingAddress", enabled = false)
	public void displayReceipt() throws IOException {
		deshBoardOrder.fullReceiptDisplay();
		TestUtils.implicitelyWaitTime();
		}

	@AfterMethod(groups = "Initializations")
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}

}
