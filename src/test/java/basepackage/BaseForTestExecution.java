package basepackage;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.RegisterPage;

public class BaseForTestExecution {
	
	public WebDriver driver;
	public ExtentSparkReporter aventReporter;
	public ExtentReports extent;
	protected RegisterPage registerPage;
	protected Random random;
	protected ITestResult result;
	
	
	@BeforeClass
	public void setupBrowser() {
		aventReporter = new ExtentSparkReporter("SDET_Task.html");
		extent = new ExtentReports();
		extent.attachReporter(aventReporter);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		random = new Random();
		//goToRegisterPage();	
		registerPage = new RegisterPage(driver);
	}
	

	@BeforeMethod
	public void goToRegisterPage() {
		driver.get("https://www.phptravels.net");
		driver.manage().deleteAllCookies();
		driver.get("https://www.phptravels.net/register");
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void RecordFailure(ITestResult result) {
		if(ITestResult.FAILURE == result.getStatus()) {
		TakesScreenshot camera = (TakesScreenshot)driver;
		File screenshot = camera.getScreenshotAs(OutputType.FILE);
		/**
		 * Don't forget to create a file in your project called screenshots if its not created in order to save the screenshots for failure inside it.
		 */
	    try {
			Files.move(screenshot, new File("screenshots\\"+result.getName()+".png")); 
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
		
	}
	
	
	@AfterClass
	public void quiteBrowser() {
		extent.flush();
		driver.quit();
	}
	
	
	
	
	
}
