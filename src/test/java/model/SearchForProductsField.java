package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchForProductsField extends HomePage {

    @FindBy(className = "search-field")
    public WebElement searchInput;
    @FindBy(css = "input[value='Search']")
    public WebElement searchButton;

    public SearchForProductsField(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SearchForProductsField FillProductName(String productName) {
        return FillInputFieldAndReturnNewView(searchInput, productName, SearchForProductsField.class);
    }

    public ProductPage ClickSearchForProduct() {
        return ClickAndReturnNewView(searchButton, ProductPage.class);
    }
}