package com.Tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Analyzer.AllureListeners;
import com.Base.TestBase;
import com.Pages.DeshBoardHome;
import com.Pages.DeshBoardOrder;
import com.Pages.HomePage;
import com.Pages.LoginPage;
import com.Utility.TestUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({AllureListeners.class})
@Epic("Regression Tests")
@Feature("End user Login Functions")
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
	@Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Validate Current Page Title..")
    @Story("Varify Page title is appear as per requirements.")
	public void pageTitleTest() {
		String ti = deshBoardHome.validatePageTitle();
		Assert.assertEquals(ti, "RobotSpareBin Industries Inc. - Intranet", "Title is missing!!");
		System.out.println("Page Title is : " + ti);
	}

	@Test(groups = "Order Page Functions", priority = 2, enabled = true)
	@Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Order Manue Functionalities..")
    @Story("Varify Order Manue is appear as per requirements.")
	public void orderManueTest() {
		// deshBoardOrder.orderManue();
		//TestUtils.implicitelyWaitTime();
		String txt = deshBoardOrder.orderPageDisplay();
		Assert.assertEquals(txt, "Build and order your robot!", "Expected Text is missing!!");
		System.out.println("Text Found as : " + txt+"\n");
	}

	@Test(groups = "Order Page Functions", priority = 3)
	@Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Select the size of head's for robot")
    @Story("End user able to select number of roobot Headsr from the given list.")
	public void heasdSelectionTest() {
		deshBoardOrder.headSelection(2); // Enter Index number
	}

	@Test(groups = "Order Page Functions", priority = 4)
	@Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Select the category of Body for robot")
    @Story("End user able to select their desire body category from the list of readio button..")
	public void bodySelectionTest() {
		deshBoardOrder.bodySelections(2);
	}

	@Test(groups = "Order Page Functions", priority = 5)
	@Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Select the category of Leg for robot")
    @Story("User are able to select number of roobot Leg's from the given list.")
	public void legsSelectionTest() {
		deshBoardOrder.legSelection(2);
	}

	@DataProvider
	public Object[][] getNewData() throws FileNotFoundException {
		Object[][] data = TestUtils.getTestData(sheetName);
		return data;
	}

	@Test(groups = "Order Page Functions", dataProvider = "getNewData", priority = 6)
	@Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: After selection, Entering the shipping address here.")
    @Story("Once user select their roobot, The must enter their shipping addres for shipping.")
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
	@Severity(SeverityLevel.NORMAL)
    @Description("Test Description: After completed order, user can see their receipts")
    @Story("Receipt will apprer")
	public void displayReceipt() throws IOException {
		deshBoardOrder.fullReceiptDisplay();
		TestUtils.implicitelyWaitTime();
		}

	@AfterMethod(groups = "Initializations")
	public void tearDown() {
		driver.close();
	}

}
