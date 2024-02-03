package com.Tests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import com.Analyzer.AllureListeners;
import com.Base.TestBase;
import com.Pages.HomePage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({AllureListeners.class})
@Epic("Regression Tests")
@Feature("End user Login Functions")
public class HomePageTest extends TestBase{
	HomePage homePage;
	
	@BeforeMethod(groups = "Initializations")
	public void tearUp() {
		initialization();
		homePage = new HomePage();
	}
	@Test(groups = "Home Page Functions")
	@Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Validate Current Page Title..")
    @Story("Varify Page title is appear as per requirements.")
	public void pageTitleTest() {
		String ti = homePage.pageTitle();
		Assert.assertEquals(ti, "RobotSpareBin Industries Inc.. - Intranet", "Title is missing!!");
		System.out.println("Page Title is : "+ti);
	}
	
	@Test(groups = "Home Page Functions")
	@Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Validate, I am on home Page.")
    @Story("Varify Home Page With homepage text as per requirements.")
	public void homePageText() {
		String hpText = homePage.homePageText();
		Assert.assertEquals(hpText, hpText.toString(), "Text hon found!!");
		System.out.println("Expected Text found: "+hpText);
	}
	@Test(groups = "Home Page Functions")
	@Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Home button availability.")
    @Story("Varify Home button is appear as per requirements.")
	public void homeButtonTest() {
		boolean appears = homePage.homeButton();
		Assert.assertTrue(appears);
		System.out.println("Home button is appear correctly: "+appears);
	}
	@Test(groups = "Home Page Functions")
	@Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Order button availability.")
    @Story("Varify Order button is appear as per requirements.")
	public void orderButtonTest() {
		boolean appears = homePage.orderButton();
		Assert.assertTrue(appears);
		System.out.println("Order button is appear correctly: "+appears);
	}
	
	@Test(groups = "Home Page Functions")
	@Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Login button availability.")
    @Story("Varify Login button is appear as per requirements.")
	public void loginButtonTest() {
		boolean appears = homePage.loginButton();
		Assert.assertTrue(appears);
		System.out.println("Login button is appear correctly: "+appears);
	}
	@AfterMethod(groups = "Initializations")
	 @Description("To closed the browsers")
	public void tearDown() {
		driver.close();
		
	}
	

}
