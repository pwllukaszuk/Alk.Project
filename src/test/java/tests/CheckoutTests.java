package tests;

import org.testng.annotations.Test;
import testsData.TestData;

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

        //to jest po wszystkie error message tu chyba.. do testu
//        List<WebElement> errorMessages = driver.findElements();
//        errorMessages.get(0);
    }
}
