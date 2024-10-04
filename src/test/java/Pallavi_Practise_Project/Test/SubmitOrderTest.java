package Pallavi_Practise_Project.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pallavi_Practise_Project.PageObjectModel.CartPage;
import Pallavi_Practise_Project.PageObjectModel.CheckoutPage;
import Pallavi_Practise_Project.PageObjectModel.LandingPage;
import Pallavi_Practise_Project.PageObjectModel.OrderPage;
import Pallavi_Practise_Project.PageObjectModel.ProductCatalogue;
import Pallavi_Practise_Project.PageObjectModel.confirmationPage;
import Pallavi_Practise_Project.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider="getData", groups= {"Purchase"})
	
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{
		 ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"), input.get("password")); 
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
// CartPage cartpage= new CartPage(driver);
		Boolean match = cartPage.verifyProductMatch(input.get("product"));
		Assert.assertTrue(match);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500);");
		Thread.sleep(1000);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");

		confirmationPage confirmationpage = checkoutPage.submitOrder();
		String confrimMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confrimMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
	ProductCatalogue productCatalogue = landingpage.loginApplication("pallavi+01@gmail.com", "Test@123"); 
	OrderPage orderPage= productCatalogue.goToOrderPage();
	Assert.assertTrue(orderPage.verifyOrderMatch(productName));	
	}
	
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

	List<HashMap<String, String>> data=	getJsonDataToMap(System.getProperty("user.dir") 
			+ "\\src\\test\\java\\Pallavi_Practice_Project\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	
	}
	
//	HashMap<Object, Object>	 map= new HashMap<Object, Object>();
//	map.put("email", "pallavi+01@gmail.com");
//	map.put("password", "Test@123");
//    map.put("product", "ADIDAS ORIGINAL");
//
//    HashMap<Object, Object>	 map1= new HashMap<Object, Object>();
//	map1.put("email", "Tomar+01@gmail.com");
//	map1.put("password", "Test@123");
//    map1.put("product", "ZARA COAT 3");
}
