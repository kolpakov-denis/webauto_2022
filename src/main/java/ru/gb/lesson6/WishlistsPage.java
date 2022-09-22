package ru.gb.lesson6;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishlistsPage extends PageView {

    public WishlistsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "submitWishlist")
    private WebElement submitWishlistBtn;

    public void createNewWishlist (String wishlistName) {
        nameField.sendKeys(wishlistName);
        submitWishlistBtn.click();
        Assertions.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'" + wishlistName + "')]")).isDisplayed());
    }
}
