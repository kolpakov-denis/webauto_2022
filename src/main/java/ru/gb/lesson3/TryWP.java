package ru.gb.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TryWP {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get("https://raincat.4otaku.org/izanagi");

        driver.findElement(By.id("user_login")).sendKeys("Test");
        driver.findElement(By.id("user_pass")).sendKeys("1qaz@WSX3edc");
        driver.findElement(By.id("wp-submit")).click();


        driver.quit();
    }
}
