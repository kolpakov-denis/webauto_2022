package ru.gb.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class LoginTest extends BasedStart {
    @Test
    @DisplayName("Login test")
    void userLogin() {

        getDriver().findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("veinzettel@ya.ru");
        getDriver().findElement(By.xpath("//input[@name='passwd']")).sendKeys("!QAZ2wsx");
        getDriver().findElement(By.id("SubmitLogin")).click();

        Assertions.assertEquals("My account - My Store", getDriver().getTitle());
    }
}
