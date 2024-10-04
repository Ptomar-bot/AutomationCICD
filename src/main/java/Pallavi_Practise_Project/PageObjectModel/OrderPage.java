package Pallavi_Practise_Project.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponentsPackage.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;

	public OrderPage(WebDriver driver) // constructor
	{
		// Intiatlization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean verifyOrderMatch(String productName) {
		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}



}
