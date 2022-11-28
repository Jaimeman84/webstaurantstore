package com.seleniumJavaWebstaurantStore.pages;

import com.seleniumJavaWebstaurantStore.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static com.seleniumJavaWebstaurantStore.util.Helper.getExplicitWait;

public class PageObject extends DriverManager {

    public WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement findElementByCSS(String locator) throws IOException {
        By by = By.cssSelector(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(getExplicitWait()));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement findElementByXpath(String locator) throws IOException {
        By by = By.xpath(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(getExplicitWait()));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement findElementById(String locator) throws IOException {
        By by = By.id(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(getExplicitWait()));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement findElementByClass(String locator) throws IOException {
        By by = By.className(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(getExplicitWait()));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement findElementBy(By by) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(getExplicitWait()));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public List<WebElement> findElementsBy(By by) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(getExplicitWait()));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public void typeIntoTextField(By by, String text) throws IOException {
        WebElement textField = findElementBy(by);
        textField.clear();
        textField.sendKeys(text);
    }

    public void typeIntoTextField(WebElement textField, String text) {
        textField.clear();
        textField.sendKeys(text);
    }

    public void clickElement(By by) throws IOException {
        WebElement element = findElementBy(by);
        element.click();
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public String getTextFromElement(By by) throws IOException {
        WebElement element = findElementBy(by);
        return element.getText();
    }

    public String getTextFromElement(WebElement element) {
        return element.getText();
    }

    public String getAttribitueFromElement(By by, String attribute) throws IOException {
        WebElement element = findElementBy(by);
        return element.getAttribute(attribute);
    }

    public String getAttributeFromElement(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    public void selectFromDropDown(By by, String text) throws IOException {
        WebElement dropDown = findElementBy(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }

    public void selectFromDropDown(WebElement dropDown, String text) {
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }

    public void selectCheckBox(By by) throws IOException {
        WebElement checkBox = findElementBy(by);
        checkBox.click();
    }

    public boolean isChecked(By by) throws IOException {
        WebElement checkBox = findElementBy(by);
        return checkBox.isSelected();
    }

    public boolean isDisplayed(By by) throws IOException {
        WebElement el = findElementBy(by);
        return el.isDisplayed();
    }

    public boolean isTextPresent(String text) {
        String pageSource = driver.getPageSource();
        return pageSource.contains(text);
    }
}
