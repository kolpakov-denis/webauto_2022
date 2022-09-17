package ru.gb.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class AddToCartTest extends BasedStart {
    @Test
    @DisplayName("Add to cart test")
    void addToCart () {

        getDriver().findElement(By.cssSelector(".sf-menu > li:nth-child(2) > a:nth-child(1)")).click();
        getDriver().findElement(By.xpath("//input[@id='layered_category_10']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-image-container']")));
        actions.moveToElement(getDriver().findElement(By.xpath("//div[@class='product-image-container']"))).perform();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Add to cart']")));
        getDriver().findElement(By.xpath("//a[@title='Add to cart']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".button-medium:nth-child(2) > span")));
        getDriver().findElement(By.cssSelector(".button-medium:nth-child(2) > span")).click();

        Assertions.assertEquals("Order - My Store", getDriver().getTitle());
    }
}
