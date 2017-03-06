package Pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Common.AmazoneCommon;

public class AmazoneResultsPage extends AmazoneCommon{
	
	public String productFound="";
	public WebDriver driver;
	@FindBy(id="twotabsearchtextbox")
	public WebElement searchBox;
	
	@FindBy(xpath=".//*[@id='nav-search']/form/div[2]/div/input")
	public WebElement searchButton;
		
	@FindBy(id="s-results-list-atf")
	List<WebElement> resultsList;
	
	@FindBy(xpath=".//*[@id='result_3']/div/div/div/div[2]/div[2]/div[1]/a/h2")
	public WebElement productThree;
	
	@FindBy(xpath="result_0")
	public WebElement results;
	
	@FindBy(id="add-to-cart-button")
	public WebElement addToCartButton;
	
	@FindBy(id="huc-v2-order-row-messages")
	public WebElement messages;

	public AmazoneResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	public void searchProduct(String product){
		sendKeysElement(searchBox, driver, product);
		clickOnElement(searchButton, driver);
		isElementLoaded(productThree, driver);
	}
	
	public boolean productThreeDisplayed(){
    	return productThree.isDisplayed();
    }
		
	
	public String addToCart() {
		clickOnElement(productThree, driver);
		clickOnElement(addToCartButton, driver);
		isElementLoaded(messages, driver);
		return getTextElement(messages, driver);
	}
	
	

}
