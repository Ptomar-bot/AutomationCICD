package Pallavi_Practise_Project.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponentsPackage.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) // constructor
	{
		// Intiatlization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail= driver.findElement(By.id("userEmail"));
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	By productsBy = By.cssSelector(".mb-3");
	By addcart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() throws InterruptedException // Action Method
	{
		waitforElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) throws InterruptedException {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addcart).click();
		waitforElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);

	}

}
