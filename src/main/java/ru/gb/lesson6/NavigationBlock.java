package ru.gb.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NavigationBlock extends PageView {
    public NavigationBlock(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = ".sf-menu > li:nth-child(2) > a:nth-child(1)")
    private WebElement dressesMainButtonLink;

  /*  @FindBy(xpath = "//li/a[.='Women']")
    private WebElement womenButton;

    @FindBy(xpath = "//ul[contains(@class, 'submenu')]//a[.='T-shirts']")
    private WebElement tShirtsButtonInSubmenu;

   */

    @Step("Кликаем по кнопке\"Dresses\"")
    public DressesPage clickDresses() {
        dressesMainButtonLink.click();
        return new DressesPage(driver);
    }

}
