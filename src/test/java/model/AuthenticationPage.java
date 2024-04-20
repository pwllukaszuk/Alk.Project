package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationPage extends BasePage {

    @FindBy(id = "username")
    WebElement emailInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(css = ".woocommerce-button.button.woocommerce-form-login__submit")
    WebElement signInButton;

    @FindBy(className = "woocommerce-error")
    public WebElement errorMessage;

    public AuthenticationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AuthenticationPage FillEmailInput(String emailAddress) {
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(emailAddress);

        return new AuthenticationPage(driver);
    }

    public AuthenticationPage FillPasswordInput(String passwordAddress) {
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(passwordAddress);

        return new AuthenticationPage(driver);
    }

    public AuthenticationPage SelectSignIn() {
        signInButton.click();
        return new AuthenticationPage(driver);
    }
}
