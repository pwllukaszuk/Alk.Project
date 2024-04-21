package model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends HomePage {

    @FindBy(id = "place_order")
    public WebElement buyAndPayButton;
    @FindBy(className = "woocommerce-error")
    public WebElement errorMessage;
    @FindBy(xpath = "//div[contains(@class, 'blockUI') and contains(@class, 'blockOverlay')]")
    public WebElement blockOverlay;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage SelectBuyAndPayWithoutCheckoutData() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buyAndPayButton);

        // For some reason wait.until methods are not working here. Sometimes exception is thrown.
        // In order to stabilize this test try catch block added.
        try
        {
            wait.until(ExpectedConditions.invisibilityOf(blockOverlay));
            wait.until(ExpectedConditions.elementToBeClickable(buyAndPayButton));

            buyAndPayButton.click();
            System.out.printf("%s clicked successfully!", buyAndPayButton);
        } catch (ElementClickInterceptedException e) {
            wait.until(ExpectedConditions.invisibilityOf(blockOverlay));
            wait.until(ExpectedConditions.elementToBeClickable(buyAndPayButton));

            buyAndPayButton.click();
            System.out.printf("%s was not clicked successfully for the first time!. Retrying.", buyAndPayButton);
        }

        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return new CheckoutPage(driver);
    }
}
