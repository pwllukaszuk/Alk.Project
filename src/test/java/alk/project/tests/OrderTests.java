package alk.project.tests;

import org.testng.annotations.Test;
import alk.project.testData.TestData;

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
    public void ProductAddedToCart() throws InterruptedException {
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
        softAssert.assertTrue(cartPage.productImage.isDisplayed(), "Product image not displayed");
        softAssert.assertTrue(cartPage.removeProductButton.isDisplayed(), "Remove product button not displayed");
        softAssert.assertEquals(cartPage.quantity.getText(), "5", "Quantity not equal");
        softAssert.assertTrue(cartPage.quantityLabel.isDisplayed(), "Quantity label not displayed");
        softAssert.assertTrue(cartPage.productNameLabel.isDisplayed(), "Product name label not displayed");
        softAssert.assertTrue(cartPage.productPriceLabel.isDisplayed(), "Product price label not displayed");
        softAssert.assertTrue(cartPage.productQuantityLabel.isDisplayed(), "Product quantity label not displayed");
        softAssert.assertEquals(cartPage.productSubTotal.isDisplayed(), "Product subtotal label not displayed");
        softAssert.assertEquals(cartPage.productName.getText(), "KOSZULKA MANCHESTER UNITED", "Product name not equal");
        softAssert.assertEquals(cartPage.productPrice.getText(), "170 ZŁ", "Product price not equal");
        softAssert.assertEquals(cartPage.totalProductsPrice.getText(), "850 ZŁ", "Total price not equal");

        softAssert.assertAll();
    }

    @Test
    public void WrongDiscountCodeProvidedTest() {
        //Arrange
        var cartPage = homePage
                .SelectSearchForProducts()
                .FillProductName(TestData.ManchesterUnitedShirt)
                .ClickSearchForProduct()
                .SelectAddToCart()
                .SelectSeeCart()
                .FillDiscountCode(TestData.DiscountCode)

                //Act
                .SelectSubmitCode();

        //Assert
        softAssert.assertTrue(cartPage.errorMessage.isDisplayed(), "Error message is not displayed");
        softAssert.assertEquals(cartPage.errorMessage.getText(), String.format("Kupon \"%s\" nie istnieje!", TestData.DiscountCode));

        softAssert.assertAll();
    }

    @Test
    public void DiscountCodeNotProvidedTest() {
        //Arrange
        var cartPage = homePage
                .SelectSearchForProducts()
                .FillProductName(TestData.ManchesterUnitedShirt)
                .ClickSearchForProduct()
                .SelectAddToCart()
                .SelectSeeCart()

                //Act
                .SelectSubmitCode();

        //Assert
        softAssert.assertTrue(cartPage.errorMessage.isDisplayed(), "Error message is not displayed");
        softAssert.assertEquals(cartPage.errorMessage.getText(), "Proszę wpisać kod kuponu.");

        softAssert.assertAll();
    }

    @Test
    public void UpdateCartTest() {
        //Arrange
        var cartPage = homePage
                .SelectSearchForProducts()
                .FillProductName(TestData.ManchesterUnitedShirt)
                .ClickSearchForProduct()
                .SelectAddToCart()
                .SelectSeeCart()
                .FillProductQuantity("10")

                //Act
                .UpdateCart();

        //Assert
        softAssert.assertTrue(cartPage.informationMessage.isDisplayed(), "Information message is not displayed");
        softAssert.assertEquals(cartPage.informationMessage.getText(), "Koszyk zaktualizowany.");

        softAssert.assertAll();
    }
}
