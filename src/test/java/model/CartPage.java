package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CartPage FillDiscountCode(String productName) {
        return FillInputFieldAndReturnNewView(discountCodeInput, productName, CartPage.class);
    }

    public CartPage FillProductQuantity(String quantity) {
        return FillInputFieldAndReturnNewView(productQuantity, quantity, CartPage.class);
    }

    public CartPage SelectSubmitCode() {
        return ClickAndReturnNewView(submitCodeButton, CartPage.class);
    }

    public CartPage UpdateCart() {
        return ClickAndReturnNewView(updateCartButton, CartPage.class);
    }
}
