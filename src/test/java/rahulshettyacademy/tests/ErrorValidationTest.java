package rahulshettyacademy.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponents.BaseTest;
import rahulshettyacademy.testcomponents.Retry;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ErrorValidationTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, dataProvider = "getInvalidCredentials", retryAnalyzer = Retry.class)
    public void loginErrorValidationTest(HashMap<String, String> invalidCredential) throws IOException {

        ProductCatalogue productCatalogue = landingPage.login(invalidCredential.get("email"), invalidCredential.get("password"));

        Assert.assertEquals(landingPage.getLoginErrorMsg(), "Incorrect email or password.");


    }


    @Test
    public void cartErrorValidationTest() {

        //login page
        String email = "unknownuuid8123712@mailinator.com";
        String password = "Test1234@!";
        ProductCatalogue productCatalogue = landingPage.login(email, password);


        //product page
        String productToAdd = "ZARA CoAT 3";
        productCatalogue.addToCart(productToAdd);
        CartPage cartPage = productCatalogue.clickCartIcon();

        //cart page
        Assert.assertFalse(cartPage.isDisplayedInTheCart("ZARA MAE 4"));
        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();

    }


    @DataProvider
    public Object[][] getInvalidCredentials() throws IOException {

        //invalid email
        //invalid password
        //invalid email and password

        List<HashMap<String, String>> listOfInvalidCredentials = getDataFromJson("/src/test/java/rahulshettyacademy/data/LoginErrorValidation.json");

        return new Object[][]{{listOfInvalidCredentials.get(0)}
                , {listOfInvalidCredentials.get(1)}, {listOfInvalidCredentials.get(2)}};

    }


}
