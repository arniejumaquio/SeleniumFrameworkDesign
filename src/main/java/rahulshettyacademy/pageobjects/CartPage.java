package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.BasePage;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//button[text()='Checkout']")
    private WebElement checkoutButton;


    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public CheckoutPage clickCheckoutButton() {
        checkoutButton.click();

        return new CheckoutPage(driver);
    }


    public boolean isDisplayedInTheCart(String productAdded) {

        boolean isDisplayedInTheCart = getCartItems().stream()
                .anyMatch(cartItemName -> cartItemName.getText().equalsIgnoreCase(productAdded));

        return isDisplayedInTheCart;
    }

}
