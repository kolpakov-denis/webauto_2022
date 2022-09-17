package ru.gb.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WishlistCreationTest extends BasedStart {
    LoginTest loginTest = new LoginTest();

    @Test
    @DisplayName("Login test")
    public void setLoginTest(){
        loginTest.userLogin();
    }

    @Test
    @DisplayName("Wishlist creation test")
    public void createWishlistTest() throws InterruptedException {
        getDriver().findElement(By.xpath("//a[@title='My wishlists']")).click();
        getDriver().findElement(By.id("name")).sendKeys("MyNewList");
        getDriver().findElement(By.id("submitWishlist")).click();
        WebElement newList = getDriver().findElement(By.xpath("//a[contains(text(),'MyNewList')]"));
        Assertions.assertEquals(true, newList.isDisplayed());
    }

}
