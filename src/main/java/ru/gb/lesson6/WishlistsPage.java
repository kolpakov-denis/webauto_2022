package ru.gb.lesson6;

import io.qameta.allure.Step;
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

    @Step("Создаём новый вишлист (передаём имя и кликаем \"Submit\"")
    public WishlistsPage createNewWishlist (String wishlistName) {

        nameField.sendKeys(wishlistName);
        submitWishlistBtn.click();
        return this;
    }
    @Step("Проверяем создание заданного вишлиста")
    public void assertNewWishlist(String wishlistName) {
        Assertions.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'" + wishlistName + "')]")).isDisplayed());

    }


}
