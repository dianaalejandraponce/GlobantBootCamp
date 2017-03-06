package Common;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Pages.AmazoneHomePage;
import Pages.AmazoneResultsPage;

public class AmazoneCommon {

	protected WebDriver driver;
	protected AmazoneResultsPage amazoneResultsPage;
	protected AmazoneHomePage amazoneHomePage;
	
	
	@BeforeMethod(alwaysRun=true)
	public void startDriver(){
		System.setProperty("webdriver.gecko.marionnette", "/Users/macuser/Documents/Selenium/geckodriver");
		driver = new FirefoxDriver();
		amazoneResultsPage = new AmazoneResultsPage(driver);
		amazoneHomePage = new AmazoneHomePage(driver);
	}
	

	@AfterMethod (alwaysRun=true)
	public void executeAfterMethod(ITestResult testResult, ITestContext testContext) throws IOException{ 
		if (testResult.getStatus() == ITestResult.FAILURE || testResult.getStatus() == ITestResult.SKIP) { 
			takeScreenShot(testContext.getName());			
		} 
		System.out.println("The test '" + testContext.getName() + "' has ended");
		driver.quit();
	}
	
	public void clickOnElement(WebElement element, WebDriver driver){
		isElementLoaded(element, driver);
		element.click();	
	}
	
	public void sendKeysElement (WebElement element, WebDriver driver, String key){
		isElementLoaded(element, driver);
		element.clear();
		element.sendKeys(key);		
	}
	
	public String getTextElement(WebElement element, WebDriver driver){
		isElementLoaded(element, driver);
		return element.getText();
	}
		
	public WebElement isElementLoaded(WebElement elementToBeLoaded, WebDriver driver) {
	    WebDriverWait wait = new WebDriverWait(driver, 15);
	    WebElement element = wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
	    return element;
	}
	
	public void takeScreenShot(String path){
		DateFormat df = new SimpleDateFormat("ddMMyy-HHmmss");
		String fullPath = "/Users/macuser/Documents/workspace/screenshoots/" + path + "/" + df.format(new Date()) + ".jpg";		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(fullPath));
			System.out.println("Screenshot on " + fullPath);
		} catch (IOException e) {	
			System.out.println("Error presented on taking the screenshot " + e.getMessage());
			e.printStackTrace();
		} 		
	}

}
