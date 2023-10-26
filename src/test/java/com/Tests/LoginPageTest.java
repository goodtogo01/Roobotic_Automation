package com.Tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Analyzer.AllureListeners;
import com.Base.TestBase;
import com.Pages.HomePage;
import com.Pages.LoginPage;
import com.Utility.*;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;

@Listeners({AllureListeners.class})
@Epic("Regression Tests")
@Feature("End user Login Functions")
public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	TestUtils testUtils;
	LoginPageTest loginPageTest;
	SeleniumOperations seleniumOperations;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod(groups = "Initializations")
	public void tearUp() throws MalformedURLException {
		initialization();
		//initializationSauseLab();
		loginPage = new LoginPage();
		testUtils = new TestUtils();
		loginPageTest = new LoginPageTest();
		seleniumOperations = new SeleniumOperations();
	}

	@Test(priority = 1, groups = "Home Page Functions")
	@Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Validate Current Page Title..")
    @Story("Varify Page title is appear as per requirements.")
	public void pageTitleTest() {
		//String ti = loginPage.validatePageTitle();
		String title = seleniumOperations.getCurrentPageTitle();
		Assert.assertEquals(title, "RobotSpareBin Industries Inc. - Intranet", "Title is missing!!");
		System.out.println("Page Title is : " + title);
	}

	@Test(priority = 2, groups = "Login page Functionalities")
    @Severity(SeverityLevel.MINOR)
    @Description("Test Description: Login test with Correct username and wrong password.")
    @Story("Valid username and password login test")
	public void loginTestWithValidCredentials() {
		loginPage.enterCredentials(prop.getProperty("userName"), prop.getProperty("password"));
		pageTitleTest();
	}

	@Test(priority = 3, groups = "Login page Functionalities")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Login test with invalid username and Empty password.")
    @Story("Invalid username and password login test")
	public void loginTestInvalidCredentials() throws IOException {
		loginPage.enterCredentials(prop.getProperty("invalidUser"), prop.getProperty("invalidPass"));
		testUtils.implicitelyWaitTime();
		if(loginPage.invalidEntry()== true) {
			assertTrue(true);
		}
		testUtils.takeScreenShoot();
		pageTitleTest();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 5,  groups = "Login page Functionalities")
	@Severity(SeverityLevel.NORMAL)
    @Description("Test Description: After Login, End user name will be appear")
    @Story("User Name Test")
	public void displayNameTest() {
		loginTestWithValidCredentials();
		TestUtils.implicitelyWaitTime();
		String name = loginPage.displayUserName();
		Assert.assertEquals(name, "maria", "Expected user not found!!");
		System.out.println("Display name found as : "+name);
	}
	@Test(priority = 6,  groups = "Logout from the current login")
	@Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Log out and capture a screenshot for further reference.")
    @Story("Log out Test")
	public void logOutTest() throws IOException {
		loginTestWithValidCredentials();
		TestUtils.implicitelyWaitTime();
		loginPage.logOut();
		
	}

	@AfterMethod(groups = "Initializations")
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.close();
		
	}

}
