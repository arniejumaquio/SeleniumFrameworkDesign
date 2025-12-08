package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.BasePage;

import java.util.List;

public class ProductCatalogue extends BasePage {


    public ProductCatalogue(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Note: Page object classes should only contain element and actions.Not data


    @FindBy(css = "div.toast-bottom-right.toast-container")
    private WebElement toastMessage;

    @FindBy(css = ".mb-3")
    private List<WebElement> products;


    By ADD_TO_CART = By.cssSelector(".card-body button:last-of-type");

    public List<WebElement> getProducts() {
        waitForElementToDisappear(toastMessage);
        return products;
    }


    public WebElement getProductName(String productToAdd) {
        WebElement productName = getProducts().stream()
                .filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productToAdd))
                .findFirst()
                .orElse(null);

        return productName;
    }

    public void addToCart(String name) {


        WebElement productNameAddToCartButton = getProductName(name).findElement(ADD_TO_CART);
        productNameAddToCartButton.click();

        waitForElementToDisappear(spinner);
        waitForElementToDisappear(toastMessage);

    }


}
