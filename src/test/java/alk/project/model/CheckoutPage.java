package alk.project.model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class CheckoutPage extends BasePage {
    @FindBy(id = "place_order")
    public WebElement buyAndPayButton;
    @FindBy(id = "billing_first_name")
    public WebElement firstNameInput;
    @FindBy(id = "billing_last_name")
    public WebElement lastNameInput;
    @FindBy(id = "billing_address_1")
    public WebElement streetInput;
    @FindBy(id = "billing_postcode")
    public WebElement postCodeInput;
    @FindBy(id = "billing_city")
    public WebElement cityInput;
    @FindBy(id = "billing_phone")
    public WebElement phoneNumberInput;
    @FindBy(id = "billing_email")
    public WebElement emailInput;
    @FindBy(className = "woocommerce-error")
    public WebElement errorMessage;
    @FindBy(xpath = "//div[contains(@class, 'blockUI') and contains(@class, 'blockOverlay')]")
    public WebElement blockOverlay;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage FillWholeForm(String firstName, String lastName, String street, String email, String phoneNumber, String postCode, String city) {
        FillFirstName(firstName);
        FillLastName(lastName);
        FillStreet(street);
        FillEmail(email);
        FillPhoneNumber(phoneNumber);
        FillPostCode(postCode);
        FillCity(city);

        return new CheckoutPage(driver);
    }

    public CheckoutPage FillFirstName(String firstName) {
        return FillInputFieldAndReturnNewView(firstNameInput, firstName, CheckoutPage.class);
    }

    public CheckoutPage FillLastName(String lastName) {
        return FillInputFieldAndReturnNewView(lastNameInput, lastName, CheckoutPage.class);
    }

    public CheckoutPage FillStreet(String street) {
        return FillInputFieldAndReturnNewView(streetInput, street, CheckoutPage.class);
    }

    public CheckoutPage FillEmail(String email) {
        return FillInputFieldAndReturnNewView(emailInput, email, CheckoutPage.class);
    }

    public CheckoutPage FillPhoneNumber(String phoneNumber) {
        return FillInputFieldAndReturnNewView(phoneNumberInput, phoneNumber, CheckoutPage.class);
    }

    public CheckoutPage FillPostCode(String postCode) {
        return FillInputFieldAndReturnNewView(postCodeInput, postCode, CheckoutPage.class);
    }

    public CheckoutPage FillCity(String city) {
        return FillInputFieldAndReturnNewView(cityInput, city, CheckoutPage.class);
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

    public OrderConfirmationAndDetails SelectBuyAndPayWithCheckoutData() {
        wait.until(ExpectedConditions.invisibilityOf(blockOverlay));
        wait.until(ExpectedConditions.elementToBeClickable(buyAndPayButton));

        buyAndPayButton.click();

        wait.until(ExpectedConditions.invisibilityOf(buyAndPayButton));

        return new OrderConfirmationAndDetails(driver);
    }
}
