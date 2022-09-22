package ru.gb.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AddToCartEveningDressAndWishlistCreationTest {
    WebDriver driver;
    MainPage mainPage;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-extensions");
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        Assertions.assertDoesNotThrow( ()-> driver.navigate().to("http://automationpractice.com/index.php"),
                "508 Resource Limit Is Reached");
    }
    @Test
    void buyEveninDressTest() {
        mainPage.clickSingInButton()
                .login("veinzettel@ya.ru", "!QAZ2wsx")
                .navigationBlock.clickDresses()
                .clickEveningDressChkbx()
                .addToCartByName("Printed Dress")
                .assertCartResult();



    }
    @Test
    void createWishlist() {
        mainPage.clickSingInButton()
                .login("veinzettel@ya.ru", "!QAZ2wsx")
                .myWishlistBtnClick()
                .createNewWishlist("CrazyWishlist1")
                .assertNewWishlist("CrazyWishlist1");

    }

    @AfterEach
    void kill() {
        driver.quit();
    }

}
