package tests;

import testsData.Timeouts;
import io.github.bonigarcia.wdm.WebDriverManager;
import model.SeleniumShopHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public SeleniumShopHomePage mainPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(Timeouts.ExplicitWait));
        driver.get("http://www.selenium-shop.pl");

        mainPage = new SeleniumShopHomePage(driver, wait);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        driver.quit();
    }
}