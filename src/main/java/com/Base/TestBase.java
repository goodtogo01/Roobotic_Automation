package com.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBase {
	public static WebDriver driver;
	public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<WebDriver>();

	public static Properties prop;
	public static String projectPath = System.getProperty("user.dir"); //not in use for now
	public static final String sauseLabUserName = "oauth-khosruzzaman.ny-a16bb";
	public static final String sauseLabAccessKey = "03a33318-0b0c-4588-a865-f75d7a1387ce";
	public static final String sauseLabURL = "https://" + sauseLabUserName + ":" + sauseLabAccessKey
			+ "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
	// Declare ThreadLocal Driver (ThreadLocalMap) for ThreadSafe Tests
public static String configPath ="/Users/khosruzzaman/JAVA-WORKS/Roobotic_Automation/resources/config.properties";

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(configPath);
			//InputStream fis = TestBase.class.getResourceAsStream("/config.properties");
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public WebDriver initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver");
			driver = new FirefoxDriver();
		} else {
			driver = new SafariDriver();
		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		tDriver.set(driver);
		return getDriver();

	}

	public static void initializationSauseLab() throws MalformedURLException {
		ChromeOptions caps = new ChromeOptions();
		caps.setCapability("platform", "Windows 10");
		caps.setCapability("version", "lates");
		caps.setCapability("extendedDebugging", "true");
		driver = new RemoteWebDriver(new URL(sauseLabURL), caps);

		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.navigate().refresh();
	}

	public static WebDriver getDriver() {
		// Get driver from ThreadLocalMap
		return driver; 
	}

}
/*  
 * == For Windows used only  
   	public WebDriver initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\Drivers/chromedriver");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			driver = new SafariDriver();
		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		tDriver.set(driver);
		return getDriver();

	}


    */
