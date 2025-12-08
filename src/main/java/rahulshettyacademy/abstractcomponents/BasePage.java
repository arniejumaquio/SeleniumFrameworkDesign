package rahulshettyacademy.abstractcomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderHistoryPage;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[class*='spinner-']")
    protected WebElement spinner;

    @FindBy(css = "button[routerlink='/dashboard/myorders']")
    protected WebElement orders;

    @FindBy(xpath = "//button[contains(text(),' Cart ')]")
    protected WebElement cartIcon;


    public void waitForElementToDisappear(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForElementToAppear(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToAppear(By locator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public OrderHistoryPage clickOrders() {
        orders.click();

        return new OrderHistoryPage(driver);
    }

    public CartPage clickCartIcon() {
        cartIcon.click();

        return new CartPage(driver);
    }


}
