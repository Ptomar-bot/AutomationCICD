package Pallavi_Practise_Project.PageObjectModel;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponentsPackage.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	
	public LandingPage(WebDriver driver) //constructor
	{
		super(driver); //It sending the driver from Parent to child so it will used in Abstract class
	//Intiatlization
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail=	driver.findElement(By.id("userEmail"));
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	//css= P.ng-tns-c4-4.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public ProductCatalogue loginApplication(String email, String password)   //Action Method
	{
		
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	
	}
	public String getErrorMessage()
	{
		waitforWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");	
	}

}
