package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends HomePage {

    @FindBy(css = ".cart-empty.woocommerce-info")
    public WebElement informationLabel;
    @FindBy(className = "return-to-shop")
    public WebElement returnToShopButton;
    @FindBy(id = "coupon_code")
    public WebElement discountCodeInput;
    @FindBy(className = "button")
    public WebElement submitCodeButton;
    @FindBy(className = "woocommerce-error")
    public WebElement errorMessage;
    @FindBy(className = "woocommerce-message")
    public WebElement informationMessage;
    @FindBy(css = ".input-text.qty.text")
    public WebElement productQuantity;
    @FindBy(xpath = "//button[@class='button' and @name='update_cart']")
    public WebElement updateCartButton;
    @FindBy(className = "wc-proceed-to-checkout")
    public WebElement goToCheckOutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CartPage FillDiscountCode(String discountCode) {
        return FillInputFieldAndReturnNewView(discountCodeInput, discountCode, CartPage.class);
    }

    public CartPage FillProductQuantity(String quantity) {
        return FillInputFieldAndReturnNewView(productQuantity, quantity, CartPage.class);
    }

    public CartPage SelectSubmitCode() {
        return ClickAndReturnNewView(submitCodeButton, CartPage.class);
    }

    public CartPage UpdateCart() {
        updateCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(informationMessage));

        return new CartPage(driver);
    }

    public CheckoutPage SelectGoToCheckout() {
        return ClickAndReturnNewView(goToCheckOutButton, CheckoutPage.class);
    }
}
