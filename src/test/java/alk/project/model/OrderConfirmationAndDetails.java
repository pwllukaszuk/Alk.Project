package alk.project.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationAndDetails extends BasePage {

    @FindBy(className = "woocommerce-order-details__title")
    public WebElement orderDetailsTitle;
    @FindBy(css = ".woocommerce-notice.woocommerce-notice--success.woocommerce-thankyou-order-received")
    public WebElement orderNotice;
    @FindBy(css = "li[class='woocommerce-order-overview__order order'] strong")
    public WebElement orderNumber;
    @FindBy(css = "li[class='woocommerce-order-overview__order order']")
    public WebElement orderNumberLabel;
    @FindBy(css = ".woocommerce-order-overview__date.date")
    public WebElement orderDateLabel;
    @FindBy(css = "li[class='woocommerce-order-overview__date date'] strong")
    public WebElement orderDate;
    @FindBy(css = ".woocommerce-order-overview__total.total")
    public WebElement orderTotalPriceLabel;
    @FindBy(css = ".woocommerce-order-overview__payment-method.method")
    public WebElement paymentMethodLabel;
    @FindBy(css = "li[class='woocommerce-order-overview__payment-method method'] strong")
    public WebElement paymentMethod;
    @FindBy(xpath = "(//span[@class='woocommerce-Price-amount amount'])[1]")
    public WebElement orderTotalPrice;
    @FindBy(xpath = "//p[contains(@class,'woocommerce-thankyou-order-received')]")
    public WebElement orderReceivedInformation;

    public OrderConfirmationAndDetails(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
