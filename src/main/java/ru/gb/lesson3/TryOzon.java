package ru.gb.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class TryOzon {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get("https://ozon.ru");

        driver.findElement(By.xpath("//a[@title='Подарочные сертификаты']")).click();

        Thread.sleep(20000);

        driver.findElement(By.xpath("//div[contains(@class, 'hide_is-mobile')]//div[contains(@class, 'certificate__content_isPlastic')]/a")).click();

        driver.quit();

    }
}
