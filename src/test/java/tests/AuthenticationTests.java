package tests;

import testsData.TestData;
import org.testng.annotations.*;

public class AuthenticationTests extends BaseTest {

    private final String errorMessage = "Error message is not displayed";

    @Test
    public void SignInValidationTest() {
        //Step 1
        //Arrange
        var authenticationPage = homePage
                .SelectMyAccount()
                .FillPassword(TestData.IncorrectUserPassword)

                //Act
                .SelectSignIn();

        //Assert
        softAssert.assertTrue(authenticationPage.errorMessage.isDisplayed(), errorMessage);
        softAssert.assertEquals(authenticationPage.errorMessage.getText(), "Błąd: Nazwa użytkownika jest wymagana.");

        //Step 2
        //Arrange
        authenticationPage
                .ClearPasswordInput()
                .FillEmail(TestData.IncorrectUserEmail)

                //Act
                .SelectSignIn();

        //Assert
        softAssert.assertTrue(authenticationPage.errorMessage.isDisplayed(), errorMessage);
        softAssert.assertEquals(authenticationPage.errorMessage.getText(), "Error: The password field is empty.");

        //Step 3
        //Arrange
        authenticationPage
                .FillPassword(TestData.IncorrectUserPassword)

                //Act
                .SelectSignIn();

        //Assert
        softAssert.assertTrue(authenticationPage.errorMessage.isDisplayed(), errorMessage);
        softAssert.assertEquals(authenticationPage.errorMessage.getText(), "Nieznana użytkownik. Proszę sprawdzić ponownie lub spróbować swój email.");

        //Step 4
        //Arrange
        authenticationPage
                .ClearEmailInput()
                .ClearPasswordInput()

                //Act
                .SelectSignIn();

        //Assert
        softAssert.assertTrue(authenticationPage.errorMessage.isDisplayed(), errorMessage);
        softAssert.assertEquals(authenticationPage.errorMessage.getText(), "Błąd: Nazwa użytkownika jest wymagana.");

        softAssert.assertAll();
    }

    @Test
    public void ResetPasswordWithIncorrectEmail() {
        //Arrange
        var resetPasswordPage = homePage
                .SelectMyAccount()
                .SelectResetPassword()

                //Act
                .ClickResetPasswordButton();

        //Assert
        softAssert.assertTrue(resetPasswordPage.errorMessage.isDisplayed(), errorMessage);
        softAssert.assertTrue(resetPasswordPage.emailOrUserLabel.isDisplayed(), errorMessage);
        softAssert.assertEquals(resetPasswordPage.errorMessage.getText(), "Wpisz nazwę użytkownika lub e-mail.");

        //Arrange
        resetPasswordPage
                .FillEmailInput(TestData.IncorrectUserEmail)

                //Act
                .ClickResetPasswordButton();

        //Assert
        softAssert.assertTrue(resetPasswordPage.errorMessage.isDisplayed(), errorMessage);
        softAssert.assertTrue(resetPasswordPage.emailOrUserLabel.isDisplayed(), errorMessage);
        softAssert.assertEquals(resetPasswordPage.errorMessage.getText(), "Błędna nazwa użytkownika lub adres e-mail.");

        softAssert.assertAll();
    }

    @Test
    public void SignUpIncorrectEmail() {
        //Arrange
        var authenticationPage = homePage
                .SelectMyAccount()
                .FillRegistrationEmail("testAccount@testAccount")

                //Act
                .SelectSignUp();

        //Assert
        softAssert.assertTrue(authenticationPage.errorMessage.isDisplayed(), errorMessage);
        softAssert.assertEquals(authenticationPage.errorMessage.getText(), "Błąd: Podaj poprawny adres e-mail.");

        softAssert.assertAll();
    }
}
