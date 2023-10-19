package com.Analyzer;

import org.apache.commons.math3.analysis.function.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Base.TestBase;

import io.qameta.allure.Attachment;

public class AllureListeners extends TestBase implements ITestListener {
	private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
	 //Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    public void onStart(ITestContext iTestContext) {
       System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", TestBase.getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
       System.out.println("I am in onFinish method " + iTestContext.getName());
      
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
       System.out.println(getTestMethodName(iTestResult) + " test is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
       System.out.println(getTestMethodName(iTestResult) + " test is succeed.");

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
       System.out.println(getTestMethodName(iTestResult) + " test is failed.");

        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver driver = TestBase.getDriver();
        //Allure ScreenShotRobot and SaveTestLog
        if (driver != null) { // can be use of -- if(driver instanceOf WebDriver)
            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }

        //Save a log on allure.
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
       System.out.println(getTestMethodName(iTestResult) + " test is skipped.");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
       System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

}
