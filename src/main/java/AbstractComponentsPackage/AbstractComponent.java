package AbstractComponentsPackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pallavi_Practise_Project.PageObjectModel.CartPage;
import Pallavi_Practise_Project.PageObjectModel.OrderPage;

public class AbstractComponent {
	
    WebDriver driver;
	public AbstractComponent(WebDriver driver) //Constructor
	{
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void waitforElementToAppear(By findBy) 
	{

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy)); 

	}
	
	public void waitforWebElementToAppear(WebElement findBy) 
	{

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOf(findBy)); 

	}
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartpage= new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage orderpage= new OrderPage(driver);
		return orderpage;
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(ele));

	}
	
	
}
