package ru.gb.lesson6;

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

    public WishlistsPage myWishlistBtnClick() {
        myWishlistsBtn.click();
        return new WishlistsPage(driver);
    }
}
