package rahulshettyacademy.cucumber_stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import rahulshettyacademy.pageobjects.*;
import rahulshettyacademy.testcomponents.BaseTest;

import java.io.IOException;

public class StepDefinitionImpl extends BaseTest {

    LandingPage landingPage;
    ProductCatalogue productCatalogue;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    ThankYouPage thankYouPage;
    String orderId;
    OrderHistoryPage orderHistoryPage;

    @Given("user in the ecommerce login page")
    public void user_in_the_ecommerce_login_page() throws IOException {
        landingPage = navigateToUrl();
    }


    @Given("^user login using email(.+) and password(.+)$")
    public void user_login_using_email_and_password(String email, String password) {
        productCatalogue = landingPage.login(email, password);
    }

    @When("^a product(.+) is added to cart$")
    public void a_product_is_added_to_cart(String product) {
        productCatalogue.addToCart(product);
    }

    @Then("^check if product(.+) is added to  cart$")
    public void check_if_product_is_added_to_cart(String product) {
        cartPage = productCatalogue.clickCartIcon();
        Assert.assertTrue(cartPage.isDisplayedInTheCart(product));
    }

    @When("^user fill up the checkout details with coupon$")
    public void user_fill_up_the_checkout_details() {
        checkoutPage = cartPage.clickCheckoutButton();
        String cvvCode = "123";
        String name = "unknown";
        String country = "India";
        String coupon = "rahulshettyacademy";
        checkoutPage.fillUpCheckoutDetails(cvvCode, name, coupon, country);
    }

    @Then("check if coupon is applied")
    public void check_if_coupon_is_applied() {
        Assert.assertEquals(checkoutPage.getCouponAppliedText(), "* Coupon Applied");
    }

    @When("user place the order")
    public void user_place_the_order() {
        thankYouPage = checkoutPage.clickPlaceOrderButton();
    }

    @Then("{string} is displayed")
    public void thankyou_for_the_order_is_displayed(String thankyouMsg) {
        Assert.assertEquals(thankYouPage.getThankYouLabelText(), thankyouMsg);
    }

    @And("item name and order id is displayed")
    public void item_name_and_order_id_is_displayed() {
        orderId = thankYouPage.getOrderId();
        orderHistoryPage = productCatalogue.clickOrders();
        Assert.assertTrue(orderHistoryPage.isOrderIdDisplayed(orderId));


        driver.quit();
    }

    @Then("{string} error is displayed")
    public void incorrect_email_or_password_error_is_displayed(String errorMsg) {
        Assert.assertEquals(landingPage.getLoginErrorMsg(), errorMsg);
        driver.quit();
    }

}
