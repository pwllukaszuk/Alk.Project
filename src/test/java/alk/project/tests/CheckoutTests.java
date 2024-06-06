package alk.project.tests;

import org.testng.annotations.Test;
import alk.project.testData.TestData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CheckoutTests extends BaseTest {
    SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy", Locale.forLanguageTag("pl-PL"));

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
    public void CheckoutWithProvidingCheckoutInformation() {
        //Arrange
        String expectedDate = dateFormat.format(new Date());

        var orderConfirmationAndDetails = homePage
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

        //Assert
        softAssert.assertEquals(orderConfirmationAndDetails.orderNotice.getText(), "Dziękujemy. Otrzymaliśmy Twoje zamówienie.");
        softAssert.assertEquals(orderConfirmationAndDetails.orderDetailsTitle.getText(), "Szczegóły zamówienia");
        softAssert.assertTrue(orderConfirmationAndDetails.orderNumberLabel.isDisplayed(), "Order number label is not displayed");
        softAssert.assertNotNull(orderConfirmationAndDetails.orderNumber.getText(), "Order number is not empty.");
        softAssert.assertTrue(orderConfirmationAndDetails.orderDateLabel.isDisplayed(), "Order date label is not displayed");
        softAssert.assertEquals(orderConfirmationAndDetails.orderDate.getText(), expectedDate, "Order date does not match today's date");
        softAssert.assertTrue(orderConfirmationAndDetails.orderTotalPriceLabel.isDisplayed(), "Order total price label is not displayed");
        softAssert.assertEquals(orderConfirmationAndDetails.orderTotalPrice.getText(), "170,00 zł");
        softAssert.assertTrue(orderConfirmationAndDetails.paymentMethodLabel.isDisplayed(), "Payment method label is not displayed");
        softAssert.assertNotNull(orderConfirmationAndDetails.paymentMethod.getText(), "Payment method is not empty.");
        softAssert.assertEquals(orderConfirmationAndDetails.orderReceivedInformation.getText(), "Dziękujemy. Otrzymaliśmy Twoje zamówienie.", "Order received information not equal");
        softAssert.assertAll();
    }
}
