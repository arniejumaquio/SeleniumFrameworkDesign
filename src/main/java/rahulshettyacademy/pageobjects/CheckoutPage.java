package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.BasePage;

import java.util.List;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//div[text()='CVV Code ']/following-sibling::input")
    private WebElement cvvCodeField;

    @FindBy(xpath = "//div[text()='Name on Card ']/following-sibling::input")
    private WebElement nameOnCardField;

    @FindBy(xpath = "//div[text()='Apply Coupon ']/following-sibling::input")
    private WebElement applyCouponField;

    @FindBy(xpath = "//button[text()='Apply Coupon']")
    private WebElement applyCouponButton;

    @FindBy(xpath = "//p[text()='* Coupon Applied']")
    private WebElement applyCouponText;

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    private WebElement selectCountryField;

    @FindBy(xpath = "//a[text()='Place Order ']")
    private WebElement placeOrderButton;

    By LIST_OF_COUNTRY = By.cssSelector(".list-group-item span");


    public void fillUpCheckoutDetails(String cvvCode, String name, String coupon, String country) {

        cvvCodeField.sendKeys(cvvCode);
        nameOnCardField.sendKeys(name);
        applyCouponField.sendKeys(coupon);
        applyCouponButton.click();
        waitForElementToDisappear(spinner);
        selectCountry(country);

    }

    public void selectCountry(String country) {
        Actions actions = new Actions(driver);
        actions.moveToElement(selectCountryField)
                .click()
                .sendKeys(country)
                .build()
                .perform();
        waitForElementToAppear(LIST_OF_COUNTRY);
        List<WebElement> countryOptions = driver.findElements(LIST_OF_COUNTRY);
        WebElement countryOptionToCLick = countryOptions.stream()
                .filter(countryOption -> countryOption.getText().equalsIgnoreCase(country))
                .findFirst()
                .orElse(null);

        countryOptionToCLick.click();
    }

    public String getCouponAppliedText() {

        return applyCouponText.getText();

    }

    public ThankYouPage clickPlaceOrderButton() {
        placeOrderButton.click();

        return new ThankYouPage(driver);
    }


}
