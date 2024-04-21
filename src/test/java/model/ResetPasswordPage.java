package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPasswordPage extends BasePage {
    @FindBy(id = "user_login")
    public WebElement emailOrUserInput;
    @FindBy(className = "woocommerce")
    public WebElement errorMessage;
    @FindBy(css = "label[for='user_login']")
    public WebElement emailOrUserLabel;
    @FindBy(css = ".woocommerce-Button.button")
    public WebElement resetPasswordButton;

    public ResetPasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ResetPasswordPage ClickResetPasswordButton() {
        return ClickAndReturnNewView(resetPasswordButton, ResetPasswordPage.class);
    }

    public ResetPasswordPage FillEmailInput(String userEmail) {
        return FillInputFieldAndReturnNewView(emailOrUserInput, userEmail, ResetPasswordPage.class);
    }
}
