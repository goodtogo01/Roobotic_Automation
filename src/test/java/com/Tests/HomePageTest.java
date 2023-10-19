package com.Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.html.HTMLObjectElement;

import com.Base.TestBase;
import com.Pages.HomePage;

public class HomePageTest extends TestBase{
	HomePage homePage;
	
	@BeforeMethod(groups = "Initializations")
	public void tearUp() {
		initialization();
		homePage = new HomePage();
	}
	@Test(groups = "Home Page Functions")
	public void pageTitleTest() {
		String ti = homePage.pageTitle();
		Assert.assertEquals(ti, "RobotSpareBin Industries Inc. - Intranet", "Title is missing!!");
		System.out.println("Page Title is : "+ti);
	}
	
	@Test(groups = "Home Page Functions")
	public void homePageText() {
		String hpText = homePage.homePageText();
		Assert.assertEquals(hpText, hpText.toString(), "Text hon found!!");
		System.out.println("Expected Text found: "+hpText);
	}
	@Test(groups = "Home Page Functions")
	public void homeButtonTest() {
		boolean appears = homePage.homeButton();
		Assert.assertTrue(appears);
		System.out.println("Home button is appear correctly: "+appears);
	}
	@Test(groups = "Home Page Functions")
	public void orderButtonTest() {
		boolean appears = homePage.orderButton();
		Assert.assertTrue(appears);
		System.out.println("Order button is appear correctly: "+appears);
	}
	
	@Test(groups = "Home Page Functions")
	public void loginButtonTest() {
		boolean appears = homePage.loginButton();
		Assert.assertTrue(appears);
		System.out.println("Login button is appear correctly: "+appears);
	}
	@AfterMethod(groups = "Initializations")
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}
	

}
