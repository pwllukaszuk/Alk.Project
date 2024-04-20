package tests;

import org.testng.annotations.Test;
import testsData.TestData;

public class OrderTests extends BaseTest {

    @Test
    public void InformationAboutEmptyCartDisplayedTest() {
        //Act
        var orderPage = homePage
                .SelectOrder();

        //Assert
        softAssert.assertTrue(orderPage.informationLabel.isDisplayed(), "Information label is not displayed");
        softAssert.assertEquals(orderPage.informationLabel.getText(), "Twój koszyk jest pusty.");
        softAssert.assertTrue(orderPage.returnToShopButton.isDisplayed(), "Return to shop button is not displayed");

        softAssert.assertAll();
    }

    @Test
    public void ProductAddedToCart() {
        //Arrange
        var productPage = homePage
                .SelectSearchForProducts()
                .FillProductName(TestData.ManchesterUnitedShirt)
                .ClickSearchForProduct()
                .FillQuantity("5")

                //Act
                .SelectAddToCart();

        //Assert
        softAssert.assertTrue(productPage.informationLabel.isDisplayed(), "Information label is not displayed");
        softAssert.assertTrue(productPage.seeCartButton.isDisplayed(), "See cart button is not displayed");
        softAssert.assertEquals(productPage.informationLabel.getText(),
                "Zobacz koszyk\n" + "5 × “Koszulka Manchester United” zostało dodanych do koszyka.");

        //Act
        var cartPage = productPage
                .SelectSeeCart();

        //Assert
        //TODO: Add elements to model and assert that product is visible in cart.
        softAssert.assertAll();
    }
}
