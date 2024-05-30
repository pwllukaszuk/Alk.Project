package alk.project.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationPage extends BasePage {

    @FindBy(id = "username")
    public WebElement emailInput;
    @FindBy(id = "reg_email")
    public WebElement registrationEmailInput;
    @FindBy(id = "password")
    public WebElement passwordInput;
    @FindBy(className = "woocommerce-error")
    public WebElement errorMessage;
    @FindBy(css = ".woocommerce-button.button.woocommerce-form-login__submit")
    public WebElement signInButton;
    @FindBy(css = ".woocommerce-Button.woocommerce-button.button.woocommerce-form-register__submit")
    public WebElement signUpButton;
    @FindBy(xpath = "//article//form//p[4]/a[1]")
    public WebElement resetPasswordButton;

    public AuthenticationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public AuthenticationPage FillEmail(String emailAddress) {
        return FillInputFieldAndReturnNewView(emailInput, emailAddress, AuthenticationPage.class);
    }

    public AuthenticationPage FillPassword(String password) {
        return FillInputFieldAndReturnNewView(passwordInput, password, AuthenticationPage.class);
    }

    public AuthenticationPage FillRegistrationEmail(String registrationEmail) {
        return FillInputFieldAndReturnNewView(registrationEmailInput, registrationEmail, AuthenticationPage.class);
    }

    public AuthenticationPage ClearEmailInput() {
        return ClearField(emailInput);
    }

    public AuthenticationPage ClearPasswordInput() {
        return ClearField(passwordInput);
    }

    public AuthenticationPage SelectSignIn() {
        return ClickAndReturnNewView(signInButton, AuthenticationPage.class);
    }

    public AuthenticationPage SelectSignUp() {
        return ClickAndReturnNewView(signUpButton, AuthenticationPage.class);
    }

    public ResetPasswordPage SelectResetPassword() {
        return ClickAndReturnNewView(resetPasswordButton, ResetPasswordPage.class);
    }

    private AuthenticationPage ClearField(WebElement webElement) {
        webElement.click();
        webElement.clear();
        return new AuthenticationPage(driver);
    }
}
