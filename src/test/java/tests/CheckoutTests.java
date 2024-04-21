package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import testsData.TestData;

import java.time.Duration;
import java.util.List;

public class CheckoutTests extends BaseTest {
    @Test
    public void IsNotPossibleToCheckoutWithoutProvidingCheckoutInformation() {
        //Arrange
        var checkoutPage = homePage
                .SelectSearchForProducts()
                .FillProductName(TestData.ManchesterUnitedShirt)
                .ClickSearchForProduct()
                .SelectAddToCart()
                .SelectSeeCart()
                .SelectGoToCheckout()

                //Act
                .SelectBuyAndPayWithoutCheckoutData();

        //Assert
        softAssert.assertTrue(checkoutPage.errorMessage.isDisplayed(), "Error message is not displayed");
        softAssert.assertEquals(checkoutPage.errorMessage.getText(),
                """
                        Billing Imię jest wymaganym polem.
                        Billing Nazwisko jest wymaganym polem.
                        Billing Ulica jest wymaganym polem.
                        Billing Miasto jest wymaganym polem.
                        Billing Telefon jest wymaganym polem.
                        Billing Adres email jest wymaganym polem.
                        Billing Kod pocztowy nie jest prawidłowym kodem pocztowym.""");

        softAssert.assertAll();
    }

    @Test
    public void CheckoutWithoutProvidingCheckoutInformation() throws InterruptedException {
        //Arrange
        var checkoutPage = homePage
                .SelectSearchForProducts()
                .FillProductName(TestData.ManchesterUnitedShirt)
                .ClickSearchForProduct()
                .SelectAddToCart()
                .SelectSeeCart()
                .SelectGoToCheckout()
                .FillWholeForm(TestData.FirstName,
                        TestData.LastName,
                        TestData.Street,
                        TestData.UserEmail,
                        TestData.PhoneNumber,
                        TestData.PostCode,
                        TestData.City)

                //Act
                .SelectBuyAndPayWithCheckoutData();

        Thread.sleep(Duration.ofMinutes(5));

        //Assert
        //TODO: Add model and assertions
        softAssert.assertAll();
    }
}
