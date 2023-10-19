package com.Tests;

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

@Listeners({AllureListeners.class})
public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	TestUtils testUtils;
	LoginPageTest loginPageTest;

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
	}

	@Test(priority = 1, groups = "Home Page Functions")
	public void pageTitleTest() {
		String ti = loginPage.validatePageTitle();
		Assert.assertEquals(ti, "RobotSpareBin Industries Inc..// - Intranet", "Title is missing!!");
		System.out.println("Page Title is : " + ti);
	}

	@Test(priority = 2, groups = "Login page Functionalities")
	public void loginTest() {
		loginPage.enterCredentials(prop.getProperty("userName"), prop.getProperty("password"));
		pageTitleTest();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 3,  groups = "Login page Functionalities")
	public void displayNameTest() {
		loginTest();
		TestUtils.implicitelyWaitTime();
		String name = loginPage.displayUserName();
		Assert.assertEquals(name, "maria", "Expected user not found!!");
		System.out.println("Display name found as : "+name);
	}

	@AfterMethod(groups = "Initializations")
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}

}
