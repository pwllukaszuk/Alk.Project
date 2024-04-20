package tests;

import testsData.TestData;
import org.testng.Assert;
import org.testng.annotations.*;

public class AuthenticationTests extends BaseTest {

    @Test
    public void SignInWithoutEmailTest() {
        //Arrange
        var authenticationPage = mainPage
                .SelectMyAccount()
                .FillPasswordInput(TestData.IncorrectUserPassword)

                //Act
                .SelectSignIn();

        //Assert
        Assert.assertTrue(authenticationPage.errorMessage.isDisplayed());
        Assert.assertEquals(authenticationPage.errorMessage.getText(), "Błąd: Nazwa użytkownika jest wymagana.");
    }

    @Test
    public void SignInWithoutPasswordTest() {
        //Arrange
        var authenticationPage = mainPage
                .SelectMyAccount()
                .FillEmailInput(TestData.IncorrectUserEmail)

                //Act
                .SelectSignIn();

        //Assert
        Assert.assertTrue(authenticationPage.errorMessage.isDisplayed());
        Assert.assertEquals(authenticationPage.errorMessage.getText(), "Error: The password field is empty.");
    }

    @Test
    public void SignInWithIncorrectEmailAndPasswordTest() {
        //Arrange
        var authenticationPage = mainPage
                .SelectMyAccount()
                .FillEmailInput(TestData.IncorrectUserEmail)
                .FillPasswordInput(TestData.IncorrectUserPassword)

                //Act
                .SelectSignIn();

        //Assert
        Assert.assertTrue(authenticationPage.errorMessage.isDisplayed());
        Assert.assertEquals(authenticationPage.errorMessage.getText(), "Nieznana użytkownik. Proszę sprawdzić ponownie lub spróbować swój email.");
    }
}
