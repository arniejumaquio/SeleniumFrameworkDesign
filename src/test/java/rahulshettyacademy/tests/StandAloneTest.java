package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;


public class StandAloneTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new FirefoxDriver();

        driver.get("https://rahulshettyacademy.com/client/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //login
        String email = "unknownuuid8123712@mailinator.com";
        String password = "Test1234@!";
        WebElement emailField = driver.findElement(By.id("userEmail"));
        WebElement passwordField = driver.findElement(By.id("userPassword"));
        WebElement loginButton = driver.findElement(By.cssSelector("input#login"));
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();

        //product page
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.toast-bottom-right.toast-container")));

        String productToAdd = "adiDAS orIGINAL";
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement productName = products.stream()
                .filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productToAdd))
                .findFirst()
                .orElse(null);
        WebElement productNameAddToCartButton = productName.findElement(By.cssSelector(".card-body button:last-of-type"));
        productNameAddToCartButton.click();

        webDriverWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div[class*='spinner-']"))));
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.toast-bottom-right.toast-container")));

        WebElement cartIcon = driver.findElement(By.xpath("//button[contains(text(),' Cart ')]"));
        cartIcon.click();

        //cart page
        List<WebElement> cartItemNames = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean isPresentInTheCart = cartItemNames.stream()
                .anyMatch(cartItemName -> cartItemName.getText().equalsIgnoreCase(productToAdd));
        Assert.assertTrue(isPresentInTheCart);

        WebElement checkoutButton = driver.findElement(By.xpath("//button[text()='Checkout']"));
        checkoutButton.click();

        //checkout page
        String cvvCode = "123";
        String name = "unknown";
        String country = "India";
        String coupon = "rahulshettyacademy";
        WebElement cvvCodeField = driver.findElement(By.xpath("//div[text()='CVV Code ']/following-sibling::input"));
        WebElement nameOnCardField = driver.findElement(By.xpath("//div[text()='Name on Card ']/following-sibling::input"));
        WebElement applyCouponField = driver.findElement(By.xpath("//div[text()='Apply Coupon ']/following-sibling::input"));
        WebElement applyCouponButton = driver.findElement(By.xpath("//button[text()='Apply Coupon']"));
        WebElement selectCountryField = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
        WebElement placeOrderButton = driver.findElement(By.xpath("//a[text()='Place Order ']"));
        cvvCodeField.sendKeys(cvvCode);
        nameOnCardField.sendKeys(name);
        applyCouponField.sendKeys(coupon);
        applyCouponButton.click();
        webDriverWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div[class*='spinner-']"))));
        WebElement couponAppliedText = driver.findElement(By.xpath("//p[text()='* Coupon Applied']"));
        Assert.assertEquals(couponAppliedText.getText(), "* Coupon Applied");
        selectCountryField.sendKeys(country);
        List<WebElement> countryOptions = driver.findElements(By.cssSelector(".list-group-item span"));

        WebElement countryOptionToCLick = countryOptions.stream()
                .filter(countryOption -> countryOption.getText().equalsIgnoreCase(country))
                .findFirst()
                .orElse(null);
        countryOptionToCLick.click();
        placeOrderButton.click();

        //thank you page
        WebElement thankYourForOrderMsg = driver.findElement(By.cssSelector("h1.hero-primary"));
        Assert.assertEquals(thankYourForOrderMsg.getText(), "THANKYOU FOR THE ORDER.");

        String orderId = driver.findElement(By.cssSelector("tr.ng-star-inserted label"))
                .getText().split("\\|")[1]
                .trim();


        WebElement orderHistoryPageLink = driver.findElement(By.xpath("//label[@routerlink='/dashboard/myorders']"));
        orderHistoryPageLink.click();

        //order history page
        List<WebElement> orders = driver.findElements(By.cssSelector("tr.ng-star-inserted"));
        boolean isOrderIdPresent = orders.stream()
                .anyMatch(order -> order.getText().contains(orderId));
        boolean isItemPresent = orders.stream()
                .anyMatch(order -> order.getText().toLowerCase().contains(productToAdd.toLowerCase()));

        Assert.assertTrue(isOrderIdPresent);
        Assert.assertTrue(isItemPresent);

<<<<<<< HEAD
=======

>>>>>>> 4e4e6ad4207727e459401c4af4b723a4873f0191
        driver.quit();

    }


}

