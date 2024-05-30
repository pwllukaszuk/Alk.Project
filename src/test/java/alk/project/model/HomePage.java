package alk.project.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    @FindBy(id = "menu-item-138")
    public WebElement orderButton;
    @FindBy(id = "menu-item-136")
    public WebElement myAccountButton;
    @FindBy(className = "page-loader")
    public WebElement pageLoader;
    @FindBy(css = ".glyphicon.glyphicon-search.header-search-button")
    public WebElement searchButton;

    public HomePage(WebDriver driver) {
       super(driver);
        PageFactory.initElements(driver, this);
    }

    public AuthenticationPage SelectMyAccount() {
        wait.until(ExpectedConditions.invisibilityOf(pageLoader));

        return ClickAndReturnNewView(myAccountButton, AuthenticationPage.class);
    }

    public CartPage SelectOrder() {
        wait.until(ExpectedConditions.invisibilityOf(pageLoader));

        return ClickAndReturnNewView(orderButton, CartPage.class);
    }

    public SearchForProductsField SelectSearchForProducts() {
        wait.until(ExpectedConditions.invisibilityOf(pageLoader));

        return ClickAndReturnNewView(searchButton, SearchForProductsField.class);
    }
}
