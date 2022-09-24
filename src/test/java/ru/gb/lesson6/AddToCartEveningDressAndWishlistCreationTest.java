package ru.gb.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;
import ru.gb.lesson7.JunitExtension;
import ru.gb.lesson7.AdditionalLoger;

import java.io.ByteArrayInputStream;

public class AddToCartEveningDressAndWishlistCreationTest {
    WebDriver driver;
    MainPage mainPage;
    @RegisterExtension
    JunitExtension testWatcher = new JunitExtension();

    @Story("Первый скоп тестов")
    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-extensions");
    }

    @BeforeEach
    void initDriver() {
        driver = new EventFiringDecorator(new AdditionalLoger()).decorate(new ChromeDriver());
        mainPage = new MainPage(driver);
        Assertions.assertDoesNotThrow( ()-> driver.navigate().to("http://automationpractice.com/index.php"),
                "508 Resource Limit Is Reached");
    }
    @Test
    @Feature("Добавление в корзину")
    void buyEveninDressTest() {
        mainPage.clickSingInButton()
                .login("veinzettel@ya.ru", "!QAZ2wsx")
                .navigationBlock.clickDresses()
                .clickEveningDressChkbx()
                .addToCartByName("Printed Dress")
                .assertCartResult();



    }
    @Test
    @Feature("Создание вишлиста")
    void createWishlist() {
        mainPage.clickSingInButton()
                .login("veinzettel@ya.ru", "!QAZ2wsx")
                .myWishlistBtnClick()
                .createNewWishlist("CrazyWishlist1")
                .assertNewWishlist("CrazyWishlist1");

    }

    @AfterEach
    void kill() {
        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry log: logs) {
            Allure.addAttachment("Элемент лога браузера", log.getMessage());
        }

        testWatcher.setScreenshot(new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        driver.quit();
    }

}


