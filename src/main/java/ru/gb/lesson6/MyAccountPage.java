package ru.gb.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends PageView {
    public NavigationBlock navigationBlock;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        navigationBlock = new NavigationBlock(driver);
    }
    @FindBy(xpath = "//a[@title='My wishlists']")
    private WebElement myWishlistsBtn;
    @Step("Кликаем по кнопке \"Wishlist\"")
    public WishlistsPage myWishlistBtnClick() {
        myWishlistsBtn.click();
        return new WishlistsPage(driver);
    }
}
