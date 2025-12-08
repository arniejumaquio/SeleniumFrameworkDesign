package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.BasePage;

import java.util.List;

public class OrderHistoryPage extends BasePage {

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tr.ng-star-inserted")
    private List<WebElement> orders;

    public List<WebElement> getOrders() {
        return orders;
    }

    public boolean isOrderIdDisplayed(String orderId) {
        return getOrders().stream()
                .anyMatch(order -> order.getText().contains(orderId));
    }

    public boolean isItemNameDisplayed(String itemName) {
        return getOrders().stream()
                .anyMatch(order -> order.getText().toLowerCase().contains(itemName.toLowerCase()));

    }

}
