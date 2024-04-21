package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderDetailsPage extends BasePage {
    @FindBy(xpath = "//p[contains(@class,'woocommerce-thankyou-order-received')]")
    public WebElement orderReceivedInformation;
    @FindBy(xpath = "//th[@class='woocommerce-table__product-name product-name']")
    public WebElement productNameLabel;
    @FindBy(css = ".woocommerce-order-details__title")
    public WebElement orderDetails;

    public OrderDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
