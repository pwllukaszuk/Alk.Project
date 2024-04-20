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

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
