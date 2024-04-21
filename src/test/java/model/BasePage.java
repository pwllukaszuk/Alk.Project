package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
