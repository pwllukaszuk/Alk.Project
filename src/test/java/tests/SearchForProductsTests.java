package tests;

import org.testng.annotations.Test;
import testsData.TestData;

public class SearchForProductsTests extends BaseTest {

    @Test
    public void SearchForProductTest() {
        //Arrange
        var productPage = homePage
                .SelectSearchForProducts()
                .FillProductName(TestData.ManchesterUnitedShirt)

                //Act
                .ClickSearchForProduct();

        //Assert
        softAssert.assertTrue(productPage.productName.isDisplayed(), "Product name is not displayed");
        softAssert.assertTrue(productPage.productPrice.isDisplayed(), "Product price is not displayed");
        softAssert.assertTrue(productPage.productDescription.isDisplayed(), "Product description is not displayed");
        softAssert.assertEquals(productPage.productName.getText(), "KOSZULKA MANCHESTER UNITED");
        softAssert.assertEquals(productPage.productPrice.getText(), "170,00 ZŁ");
        softAssert.assertEquals(productPage.productDescription.getText(), "Koszulka meczowa drużyny Manchester United");

        softAssert.assertAll();
    }
}
