package Pallavi_Practise_Project.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Pallavi_Practise_Project.PageObjectModel.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

	String productName= "ADIDAS ORIGINAL";
	WebDriverManager.chromedriver().setup();
	WebDriver driver= new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://rahulshettyacademy.com/client");
	LandingPage landingpage = new LandingPage(driver);
	driver.manage().window().maximize();
	//"pallavi+01@gmail.com", "Test@123"
	driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
	driver.findElement(By.id("login")).click();
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));  
	
	List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
	WebElement prod= products.stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	//products.stream()-> iterate the all each and every products
	//filter(product)-> Once the first product retrieve it will store in product variable.
	//getText() -> Check the text  // if the text is not present then using cssselector we will scan the page.
	//equals("ADIDAS ORIGINAL")-> Compare the text
	//findFirst()-> it matches it will return first match.
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
     //.ng-animating
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

    Thread.sleep(2000);
//Click on cart
	driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']")).click();
//Iterate the list	
	List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	Assert.assertTrue(match);
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0, 500);");
	Thread.sleep(1000);
	
	driver.findElement(By.cssSelector(".totalRow button")).click();	
	Actions a = new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	
	WebElement button= driver.findElement(By.cssSelector(".action__submit"));
	button.click();
//verify the Thank you message	
	String confirmMessage=	driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	driver.close();
	}
}
