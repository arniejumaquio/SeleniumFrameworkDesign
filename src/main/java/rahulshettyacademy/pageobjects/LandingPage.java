package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.BasePage;

public class LandingPage extends BasePage {


    public LandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Note: Page object classes should only contain element and actions.Not data

    @FindBy(id = "userEmail")
    private WebElement emailField;

    @FindBy(id = "userPassword")
    private WebElement passwordField;

    @FindBy(css = "input#login")
    private WebElement loginButton;

    @FindBy(css = "div[aria-label='Incorrect email or password.']")
    private WebElement loginErrorMessage;

    public ProductCatalogue login(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();

        return new ProductCatalogue(driver);
    }

    public String getLoginErrorMsg() {
        waitForElementToAppear(loginErrorMessage);
        return loginErrorMessage.getText();
    }


}
