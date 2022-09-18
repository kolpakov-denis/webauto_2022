package ru.gb.lesson5;

import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasedStart {
    private static WebDriver driver;
    public static WebDriverWait webDriverWait;// = new WebDriverWait(driver, Duration.ofSeconds(5));
    public static Actions actions;// = new Actions(driver);

    @BeforeAll
    static void driverStart () {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-extensions");
        //  options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);

        Assertions.assertDoesNotThrow( ()-> driver.navigate().to("http://automationpractice.com/index.php"),
                "Страница не доступна"); //Нашёл на SO, показалось хорошей идеей
    }


    @AfterAll
    static void kill() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}




