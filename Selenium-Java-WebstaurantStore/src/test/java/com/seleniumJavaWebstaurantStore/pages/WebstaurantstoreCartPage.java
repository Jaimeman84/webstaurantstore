package com.seleniumJavaWebstaurantStore.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class WebstaurantstoreCartPage extends PageObject {

    public WebstaurantstoreCartPage(WebDriver driver) throws IOException {
        super(driver);
    }
    @FindBy(xpath = "//button[@class='emptyCartButton btn btn-mini btn-ui pull-right']")
    private WebElement btnEmptyCart;

    @FindBy(xpath = "//*[@id='td']/div[11]//footer/button[1]")
    private WebElement btnEmptyCartConfirmationModal;

    @FindBy(xpath = "//p[@class='header-1']")
    private WebElement txtCartEmpty;

    @FindBy(xpath = "//span[@class='itemDescription description']/a")
    private WebElement txtCartItemDescription;

    String actualValue;
    String expectedValue;

    public void validateProductInTheCartIsCorrect() throws InterruptedException {
        actualValue = getTextFromElement(txtCartItemDescription);
        expectedValue = "Advance Tabco FMS-243 24\" x 36\" 16 Gauge Stainless Steel Commercial Work Table with Undershelf and 1 1/2\" Backsplash";
        Assert.assertEquals(expectedValue, actualValue);
    }
    public void removeCartItem() throws InterruptedException {
        clickElement(btnEmptyCart);
        Thread.sleep(2000);
        clickElement(btnEmptyCartConfirmationModal);
    }

    public void validateCartIsEmpty() throws IOException {
        actualValue = getTextFromElement(txtCartEmpty);
        expectedValue = "Your cart is empty.";
        Assert.assertEquals(actualValue, expectedValue);
    }
}
