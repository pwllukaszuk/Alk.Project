package alk.project.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {
    @FindBy(className = "woocommerce-message")
    public WebElement informationLabel;
    @FindBy(className = "woocommerce-product-details__short-description")
    public WebElement productDescription;
    @FindBy(css = ".product_title.entry-title")
    public WebElement productName;
    @FindBy(css = ".input-text.qty.text")
    public WebElement productQuantity;
    @FindBy(css = ".single_add_to_cart_button.button.alt")
    public WebElement addToCartButton;
    @FindBy(css = ".button.wc-forward")
    public WebElement seeCartButton;
    @FindBy(xpath = "//p[@class='price']//span[@class='woocommerce-Price-amount amount']")
    public WebElement productPrice;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ProductPage FillQuantity(String quantity) {
        return FillInputFieldAndReturnNewView(productQuantity, quantity, ProductPage.class);
    }

    public ProductPage SelectAddToCart() {
        return ClickAndReturnNewView(addToCartButton, ProductPage.class);
    }

    public CartPage SelectSeeCart() {
        return ClickAndReturnNewView(seeCartButton, CartPage.class);
    }
}
