package com.Tests;
import java.io.FileNotFoundException;

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

@Test
@Listeners({AllureListeners.class})
@Epic("Regression Tests")
@Feature("Desh Bard Home Functions")
public class DeshBoardHomeTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	DeshBoardHome deshBoardHome;
	TestUtils testUtils;
	static String sheetName = "Sheet1";

	@BeforeMethod(groups = "Initializations")
	public void tearUp() {
		initialization();

		deshBoardHome = new DeshBoardHome();
		testUtils = new TestUtils();
		homePage = new HomePage();
		loginPage = new LoginPage();
		TestUtils.implicitelyWaitTime();

	}

	@Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Validate Current Page Title..")
    @Story("To Varify Page title is appear as per requirements.")
	public void pageTitleTest() {
		String ti = deshBoardHome.validatePageTitle();
		Assert.assertEquals(ti, "RobotSpareBin Industries Inc. - Intranet", "Title is missing!!");
		System.out.println("Page Title is : " + ti);
	}

	@DataProvider
	public Object[][] getNewData() throws FileNotFoundException {
		Object[][] data = TestUtils.getTestData(sheetName);
		return data;
	}

	@Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Entering Salse data from the Excel sheet.")
    @Story("Add external test data for each ueser and capture the behaviour")
	public void salseDataTest(String firstname, String lastname, String amounts) {
		loginPage.enterCredentials(prop.getProperty("userName"), prop.getProperty("password"));
		//TestUtils.implicitelyWaitTime();
		deshBoardHome.enterSalseData(firstname, lastname, amounts);
		//TestUtils.implicitelyWaitTime();
		deshBoardHome.displatResult();
		//TestUtils.implicitelyWaitTime();
		deshBoardHome.bossMessages();
		
	}
/*
	@Test(groups = "Desh Board Functions", priority=3)
	public void desplayResults() {
		deshBoardHome.displatResult();
	}

	@Test(groups = "Desh Board  Functions", priority = 3, dependsOnMethods ="salseDataTest" )
	public void bossMessageValidation(String firstname, String lastname, String amounts) {
		loginPage.enterCredentials(prop.getProperty("userName"), prop.getProperty("password"));
		TestUtils.implicitelyWaitTime();
		deshBoardHome.enterSalseData(firstname, lastname, amounts);
		TestUtils.implicitelyWaitTime();
		deshBoardHome.bossMessages();
	}
	*/

	@AfterMethod(groups = "Initializations")
	@Description("To closed the browsers.")
   
	public void tearDown() {
	 
		driver.close();
	
	}
}
