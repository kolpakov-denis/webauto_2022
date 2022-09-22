package ru.gb.lesson6;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DressesPage extends PageView {

    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public DressesPage (WebDriver driver) {
        super(driver);
    }

/*    @FindBy(xpath = "//div[@class='layered_filter']//input[@type='checkbox']") //Всё-таки разберусь с этим "револьвером" чекбоксов позже.
    private List<WebElement> checkboxing;

    public DressesPage selectCheckbox(String chbxName) {
        webDriverWait.until(d -> checkboxing.size() > 1);
        checkboxing.stream().filter(s -> s.getText().contains(chbxName)).findFirst().get().click();
        return this;
        }

 */
    @FindBy(xpath = "//input[@id='layered_category_10']")
    private WebElement eveningDressChkbx;

    @FindBy(xpath = "//div[@class='product-container']")
    private List<WebElement> dressesList;

    private static final String addToCartButtonXpathLocator = "//span[.='Add to cart']";
    @FindBy(xpath = addToCartButtonXpathLocator)
    private WebElement addToCartButton;

    public DressesPage clickEveningDressChkbx() {
        eveningDressChkbx.click();
        webDriverWait.until(ExpectedConditions.urlContains("categories-evening_dresses")); //Выглядит как хороший вариант ожидания для медленного сайта...
        return this;
    }



    public DressesPage addToCartByName(String dressName) {

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Add to cart']")));
        actions.moveToElement(dressesList.stream().filter(d -> d.getText().contains(dressName)).findFirst().get())
                .perform();
        dressesList.stream().filter(d -> d.getText().contains(dressName)).findFirst().get().findElement(
                By.xpath(addToCartButtonXpathLocator)).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".button-medium:nth-child(2) > span")));
        //Assertions.assertTrue(driver.findElement(By.cssSelector(".button-medium:nth-child(2) > span")).isDisplayed());
        return this;

    }

    public void assertCartResult() {
        Assertions.assertTrue(driver.findElement(By.cssSelector(".button-medium:nth-child(2) > span")).isDisplayed());
    }








}
