package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPasswordPage extends HomePage {

    @FindBy(css = "label[for='user_login']")
    public WebElement emailOrUserLabel;
    @FindBy(id = "user_login")
    public WebElement emailOrUserInput;
    @FindBy(css = ".woocommerce-Button.button")
    public WebElement resetPasswordButton;
    @FindBy(className = "woocommerce")
    public WebElement errorMessage;

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ResetPasswordPage ClickResetPasswordButton() {
        return ClickAndReturnNewView(resetPasswordButton, ResetPasswordPage.class);
    }

    public ResetPasswordPage FillEmailInput(String userEmail) {
        return FillInputFieldAndReturnNewView(emailOrUserInput, userEmail, ResetPasswordPage.class);
    }
}
