package Test;

import org.testng.annotations.Test;
import Common.AmazoneCommon;
import org.testng.Assert;
import org.testng.annotations.Parameters;

public class LaunchDriver extends AmazoneCommon {
	
	@Parameters({"page", "email", "password"})
	@Test(groups={"logIn"})
	public void startSesion(String page, String email, String password) {	
		driver.get(page);
		amazoneHomePage.LogIn(email, password);
	    Assert.assertTrue(amazoneHomePage.isCustomerImageDisplayed());
	}
	
	@Parameters({"page", "email", "password","product"})
	@Test(groups={"selectProduct"})
	public void searchAndSelectProduct(String page, String email, String password, String product) throws InterruptedException {
		driver.get(page);
		amazoneHomePage.LogIn(email, password);
		amazoneResultsPage.searchProduct(product);
        Assert.assertTrue(amazoneResultsPage.productThreeDisplayed());
	}
	
	@Parameters({"page", "email", "password","product"})
	@Test(groups={"addToCart"})
	public void addedToCart(String page, String email, String password, String product) throws InterruptedException{
		driver.get(page);
		amazoneHomePage.LogIn(email, password);
		amazoneResultsPage.searchProduct(product);
		Assert.assertTrue(amazoneResultsPage.addToCart().contains("Added to Cart"));
		
	}
	
}
