package alk.project.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {
    @FindBy(id = "coupon_code")
    public WebElement discountCodeInput;
    @FindBy(className = "return-to-shop")
    public WebElement returnToShopButton;
    @FindBy(className = "attachment-shop_isle_cart_item_image_size size-shop_isle_cart_item_image_size")
    public WebElement productImage;
    @FindBy(className = "remove")
    public WebElement removeProductButton;
    @FindBy(className = "wc-proceed-to-checkout")
    public WebElement goToCheckOutButton;
    @FindBy(className = "button")
    public WebElement submitCodeButton;
    @FindBy(className = "woocommerce-error")
    public WebElement errorMessage;
    @FindBy(className = "woocommerce-message")
    public WebElement informationMessage;
    @FindBy(css = ".input-text.qty.text")
    public WebElement productQuantity;
    @FindBy(css = ".cart-empty.woocommerce-info")
    public WebElement informationLabel;
    @FindBy(css = "label[for='quantity_6658409c2fc36']")
    public WebElement quantityLabel;
    @FindBy(css = "#quantity_6658409c2fc36")
    public WebElement quantity;
    @FindBy(css = "th[class='product-name']")
    public WebElement productNameLabel;
    @FindBy(css = "th[class='product-price']")
    public WebElement productPriceLabel;
    @FindBy(css = "th[class='product-quantity']")
    public WebElement productQuantityLabel;
    @FindBy(css = "th[class='product-subtotal']")
    public WebElement productSubTotal;
    @FindBy(css = "td[class='product-name'] a")
    public WebElement productName;
    @FindBy(xpath = "//button[@class='button' and @name='update_cart']")
    public WebElement updateCartButton;
    @FindBy(xpath = "//td[@class='product-price']//span[@class='woocommerce-Price-amount amount']")
    public WebElement productPrice;
    @FindBy(xpath = "//td[@class='product-subtotal']//span[@class='woocommerce-Price-amount amount']")
    public WebElement totalProductsPrice;

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
