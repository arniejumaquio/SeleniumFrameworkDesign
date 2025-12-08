package rahulshettyacademy.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.*;
import rahulshettyacademy.testcomponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class EndToEndTest extends BaseTest {

    public ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    String orderId;
    private static HashMap<String, String> emailAndOrderIdMapping = new HashMap<String, String>();

    @Test(dataProvider = "getLoginCredentials", groups = {"EndToEnd"})
    public void submitOrderTest(HashMap<String, String> loginCredsAndProduct) throws IOException {

        //login page
        ProductCatalogue productCatalogue = landingPage.login(loginCredsAndProduct.get("email"), loginCredsAndProduct.get("password"));

        //product page
        productCatalogue.addToCart(loginCredsAndProduct.get("product"));
        CartPage cartPage = productCatalogue.clickCartIcon();

        //cart page
        Assert.assertTrue(cartPage.isDisplayedInTheCart(loginCredsAndProduct.get("product")));
        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();


        //checkout page
        String cvvCode = "123";
        String name = "unknown";
        String country = "India";
        String coupon = "rahulshettyacademy";
        checkoutPage.fillUpCheckoutDetails(cvvCode, name, coupon, country);
        Assert.assertEquals(checkoutPage.getCouponAppliedText(), "* Coupon Applied");
        ThankYouPage thankYouPage = checkoutPage.clickPlaceOrderButton();

        //thank you page
        Assert.assertEquals(thankYouPage.getThankYouLabelText(), "THANKYOU FOR THE ORDER.");
        orderId = thankYouPage.getOrderId();
        threadLocal.set(orderId);
        emailAndOrderIdMapping.put(loginCredsAndProduct.get("email"), orderId);


    }


    @Test(dependsOnMethods = {"submitOrderTest"}, dataProvider = "getLoginCredentials", groups = {"EndToEnd"})
    public void orderHistoryTest(HashMap<String, String> loginCredsAndProduct) {
        //order history page

        orderId = emailAndOrderIdMapping.get(loginCredsAndProduct.get("email"));
        ProductCatalogue productCatalogue = landingPage.login(loginCredsAndProduct.get("email"), loginCredsAndProduct.get("password"));
        OrderHistoryPage orderHistoryPage = productCatalogue.clickOrders();
        Assert.assertTrue(orderHistoryPage.isOrderIdDisplayed(orderId));
        Assert.assertTrue(orderHistoryPage.isItemNameDisplayed(loginCredsAndProduct.get("product")));

    }

    @DataProvider
    public Object[][] getLoginCredentials() throws IOException {


        List<HashMap<String, String>> listOfData = getDataFromJson("/src/test/java/rahulshettyacademy/data/PurchaseOrder.json");
        return new Object[][]{{listOfData.get(0)}, {listOfData.get(1)}};

    }


}
