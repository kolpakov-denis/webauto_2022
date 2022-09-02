package ru.gb.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class TryFromLesson1 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.firefoxdriver().setup();

        WebDriver driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Actions flex = new Actions(driver);

        driver.get("http://automationpractice.com");

        driver.manage().window().maximize();

        driver.findElement(By.cssSelector(".sf-menu > li:nth-child(2) > a:nth-child(1)")).click();

        driver.findElement(By.xpath("//input[@id='layered_category_10']")).click();

        Thread.sleep(5000);

        flex.moveToElement(driver.findElement(By.xpath("//div[@class='product-image-container']"))).perform();

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Add to cart']")));

        driver.findElement(By.xpath("//a[@title='Add to cart']")).click();

      //  driver.findElement(By.cssSelector("a.button-medium")).click(); - пока не смог реализовать

        driver.quit();

    }
}
