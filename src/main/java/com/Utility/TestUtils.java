package com.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Base.TestBase;

public class TestUtils extends TestBase {
	public static final long IMPLICITLY_WAIT_TIME = 25;
	public static final long PAGE_LOAD_TIME = 10;
	public static WebDriverWait wait;
	public static String TestDataSheet = "user.dir";
	static Workbook book;
	static Sheet sheet;
	public static Alert alert;
	public static FileInputStream fis;

	public static Object[][] getTestData(String sheetName) throws FileNotFoundException {
		//fis = new FileInputStream(TestDataSheet);
		InputStream fis = TestBase.class.getResourceAsStream("/salesSheet.xlsx");
		try {
			book = WorkbookFactory.create(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(1).getLastCellNum()];
		System.out.println(sheet.getLastRowNum() + " Row's and ================= " + sheet.getRow(1).getLastCellNum()
				+ " Column === ");
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString(); // i + 1 = Cell 0
				System.out.println(data[i][j] + ", ");
			}
		}
		return data;
	}

	public static void takeScreenShoot() throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String curentDir = System.getProperty("user.dir");
		FileUtils.copyFile(srcFile, new File(curentDir + "/Screen_Shoot/" + System.currentTimeMillis() + ".png"));

	}

	public static void takeScreenShootSpecificElement(WebElement element) throws IOException {
		File screenShotFile = element.getScreenshotAs(OutputType.FILE);
		String curentDir = System.getProperty("user.dir");
		FileUtils.copyFile(screenShotFile,
				new File(curentDir + "/Screen_Shoot/" + System.currentTimeMillis() + ".png"));

	}

	public static void explicitelyWaitTime(String locator) {
		// wait = new WebDriverWait(driver, IMPLICITLY_WAIT_TIME);
		WebDriverWait wait = new WebDriverWait(driver, IMPLICITLY_WAIT_TIME);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
	}

	public static void implicitelyWaitTime() {
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIME, TimeUnit.SECONDS);
	}

	public static void implicitelyWaitTimeForAlert() {
		wait = new WebDriverWait(driver, IMPLICITLY_WAIT_TIME);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void selectClass(String listOfHead) {
		Select se = new Select(driver.findElement(By.xpath(listOfHead)));
		System.out.println("List of Items are : " + se.getAllSelectedOptions());
	}

	public static void alertMethod(String allertButton) {
		System.out.println("Prompt Alert.");
		WebElement element = driver.findElement(By.id(allertButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
		alert = driver.switchTo().alert();
		String text2 = alert.getText();
		System.out.println("Alert Text : " + text2);
		alert.accept();

	}

	public static void elementNotClickAble(String locator) {
		WebDriverWait wait2 = new WebDriverWait(driver, IMPLICITLY_WAIT_TIME);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

	}
	
	public static void safeJavaScriptClick(WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element "+ e.getStackTrace());
		}
	}
 

}
