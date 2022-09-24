package ru.gb.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageView {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    private WebElement submitButton;
    @Step("Вводим логин и пароль, кликаем кнопку\"Submit\"")
    public MyAccountPage login(String login, String password) {
        emailField.sendKeys(login);
        passwordField.sendKeys(password);
        submitButton.click();
        return new MyAccountPage(driver);
    }
}
