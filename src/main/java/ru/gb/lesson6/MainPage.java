package ru.gb.lesson6;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends PageView {
    @FindBy(xpath = "//a[@class='login']")
    private WebElement signInButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }
    @Step("Кликаем по кнопке \"Sign in\"")
    public LoginPage clickSingInButton() {
        signInButton.click();
        return new LoginPage(driver);
    }
}
