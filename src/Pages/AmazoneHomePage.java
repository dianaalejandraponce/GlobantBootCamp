package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Common.AmazoneCommon;

public class AmazoneHomePage extends AmazoneCommon {
	
	@FindBy(id="ap_email")
	public WebElement emailBox;
	
	@FindBy(id="ap_password")
	public WebElement passwordBox;
	
	@FindBy(id="nav-link-accountList")
	public WebElement signInLink;
	
	@FindBy(id="hud-customer-image")
	public WebElement customerImage;
	
	@FindBy(id="signInSubmit")
	public WebElement signInButton;
	
	public String user=""; 
	
	public AmazoneResultsPage amazoneResultsPage;
	
	public AmazoneCommon amazoneCommon;
	
	public WebDriver driver;
	
	public AmazoneHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		amazoneResultsPage = new AmazoneResultsPage(driver);
	}	
	public void LogIn(String email, String password){
		clickOnElement(signInLink, driver);
		isElementLoaded(emailBox, driver);
		sendKeysElement(emailBox, driver, email);
		sendKeysElement(passwordBox, driver, password);
		clickOnElement (signInButton, driver);
		isElementLoaded(customerImage, driver);
	}
	
	public boolean isCustomerImageDisplayed(){
    	return customerImage.isDisplayed();
    } 
	
	
}

