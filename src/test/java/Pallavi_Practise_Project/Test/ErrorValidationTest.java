package Pallavi_Practise_Project.Test;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pallavi_Practise_Project.PageObjectModel.CartPage;
import Pallavi_Practise_Project.PageObjectModel.ProductCatalogue;
import Pallavi_Practise_Project.TestComponents.BaseTest;
import Pallavi_Practise_Project.TestComponents.Retry; // Correct import for Retry


public class ErrorValidationTest extends BaseTest {
//New line added
	// @Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)

	public void LoginErrorValidation() throws IOException, InterruptedException {
		// String productName = "ADIDAS ORIGINAL";

		landingpage.loginApplication("pallavi+01@gmail.com", "Test@12");
		// .ng-tns-c4-4.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

	}

	@Test

	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingpage.loginApplication("pallavi+01@gmail.com", "Test@123");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductMatch("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
