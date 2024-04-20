package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumShopHomePage extends BasePage {

    @FindBy(id = "menu-item-136")
    WebElement myAccountButton;

    @FindBy(className = "page-loader")
    WebElement pageLoader;

    public SeleniumShopHomePage(WebDriver driver, WebDriverWait wait)
    {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public AuthenticationPage SelectMyAccount()
    {
        wait.until(ExpectedConditions.invisibilityOf(pageLoader));

        myAccountButton.click();
        return new AuthenticationPage(driver);
    }
}
