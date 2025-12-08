package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.BasePage;

public class ThankYouPage extends BasePage {


    public ThankYouPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h1.hero-primary")
    private WebElement thankYouLabel;

    @FindBy(css = "label.ng-star-inserted")
    private WebElement orderId;

    @FindBy(xpath = "//label[@routerlink='/dashboard/myorders']")
    private WebElement orderHistoryPageLink;


    public String getThankYouLabelText() {
        return thankYouLabel.getText();
    }

    public String getOrderId() {
        return orderId
                .getText().split("\\|")[1]
                .trim();
    }

    public OrderHistoryPage clickOrderHistoryPageLink() {
        orderHistoryPageLink.click();

        return new OrderHistoryPage(driver);
    }


}
