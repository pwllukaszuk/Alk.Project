package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;

public class HomePage {

    public WebDriver driver;
    public WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    @FindBy(id = "menu-item-136")
    public WebElement myAccountButton;
    @FindBy(className = "page-loader")
    public WebElement pageLoader;
    @FindBy(css = ".glyphicon.glyphicon-search.header-search-button")
    public WebElement searchButton;
    @FindBy(id = "menu-item-138")
    public WebElement orderButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public HomePage() {
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

    public <T> T ClickAndReturnNewView(WebElement webElement, Class<T> pageClass) {
        try {
            webElement.click();
            Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
            return constructor.newInstance(driver);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            logger.error("Error occurred while clicking button and navigating to page", e);
            return null;
        }
    }

    public <T> T FillInputFieldAndReturnNewView(WebElement inputElement, String value, Class<T> pageClass) {
        inputElement.click();
        inputElement.clear();
        inputElement.sendKeys(value);

        try {
            Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
            return constructor.newInstance(driver);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            logger.error("Error occurred while clicking button and navigating to page", e);
            return null;
        }
    }
}
