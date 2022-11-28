package com.seleniumJavaWebstaurantStore.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

import static com.seleniumJavaWebstaurantStore.util.PropKey.WebstaurantstoreUrl;

public class WebstaurantstoreHomePage extends PageObject {

    @FindBy(id = "searchval")
    private WebElement txtProductSearch;

    @FindBy(xpath = "//*[@id=\"searchForm\"]///button")
    private WebElement btnProductSearch;

    @FindBy(xpath = "//*[@class='first']/span[@class='result']/mark")
    private WebElement txtProductAutoComplete;

    @FindBy(xpath = "//div[@id='product_listing']/div[@id='ProductBoxContainer'][60]//input[@class='btn btn-cart btn-small']")
    private WebElement btnLastProductAddToCart;

    @FindBy(xpath = "//h1[@class='page-header search--title']")
    private WebElement txtSearchTitle;
    @FindBy(xpath = "//*[@id='td']/div[11]//footer/button[2]")
    private WebElement btnProductAccessoriesAddToCart;

    @FindBy(xpath = "//*[@class='btn btn-small btn-primary']")
    private WebElement btnViewCart;

    @FindBy(xpath = "//button[@class='emptyCartButton btn btn-mini btn-ui pull-right']")
    private WebElement btnEmptyCart;

    @FindBy(xpath = "//*[@id='td']/div[11]//footer/button[1]")
    private WebElement btnEmptyCartConfirmationModal;

    @FindBy(xpath = "//p[@class='header-1']")
    private WebElement txtCartEmpty;

    String resultsTitle;
    String totalResults;
    String actualValue;
    String expectedValue;
    int productIndex = 1;
    int pageIndex = 1;
    int productCount;
    boolean match;

    public WebstaurantstoreHomePage(WebDriver driver) throws IOException {
        super(driver);
    }

    public void navigateWebstaurantstore() throws IOException {
        driver.get(prop.getProperty(WebstaurantstoreUrl.getPropValue()));
    }

    public void performProductSearch(String text) {
        clickElement(txtProductSearch);
        typeIntoTextField(txtProductSearch, text);
        clickElement(txtProductAutoComplete);
    }

    public void validateResultsText() {

        /* This validation fails because there are 6 products that don't contain the word "Table"
        * Products Description Below -
        * Description - Regency 30" x 48" 16-Gauge Stainless Steel Equipment Stand with Galvanized Undershelf, 10" Plate Shelf, and 10" Stainless Steel Adjustable Work Surface
        * Description - Regency 30" x 36" 16-Gauge Stainless Steel Equipment Stand with Galvanized Undershelf and 10" Stainless Steel Adjustable Work Surface
        * Description - Regency 24" x 24" 16-Gauge Stainless Steel Equipment Stand with Galvanized Undershelf and 10" Stainless Steel Adjustable Work Surface
        * Description - Regency 30" x 36" 16-Gauge Stainless Steel Equipment Stand with Galvanized Undershelf, 10" Plate Shelf, and 10" Stainless Steel Adjustable Work Surface
        * Description - Regency 30" x 48" 16-Gauge Stainless Steel Equipment Stand with Galvanized Undershelf and 10" Stainless Steel Adjustable Work Surface
        * Description - Regency 24" x 24" 16-Gauge Stainless Steel Equipment Stand with Galvanized Undershelf, 10" Plate Shelf, and 10" Stainless Steel Adjustable Work Surface
        *
        * Is three an issues on the lookup? or is it expected?
        *
        * Another approach to validate results is to do it via API testing
        *
         */
        resultsTitle = getTextFromElement(txtSearchTitle);
        totalResults = resultsTitle.replaceAll("[^0-9]", "");

        /* Question - UI results ~5,343 do not match the total products based on pages and rows
        * There are 9 pages with 10 row and each row contains 6 products. That is a Total of 540 products
        * Is Webstaurantstore only displaying a MAX of 9 pages?
         */

        System.out.println("totalResults " + totalResults);

        for (int j = 1; j <= 9; j++) {
            pageIndex = j;

            if (pageIndex == 1) {
                WebElement btnCurrentPage = driver.findElement(By.xpath("//*[@aria-label='current page, page " + pageIndex +"']"));
                System.out.println("pageIndex " + pageIndex);
                clickElement(btnCurrentPage);;
            } else if (pageIndex != 9) {
                WebElement btnPage = driver.findElement(By.xpath("//*[@aria-label='page " + pageIndex +"']"));
                System.out.println("pageIndex " + pageIndex);
                clickElement(btnPage);
            } else {
                WebElement btnLastPage = driver.findElement(By.xpath("//*[@aria-label='last page, page " + pageIndex +"']"));
                System.out.println("pageIndex " + pageIndex);
                clickElement(btnLastPage);
            }
            for (int i = 1; i <= 60; i++) {
                productIndex = i ;
                productCount++;
                WebElement txtDescription = driver.findElement(By.xpath("//*[@id='ProductBoxContainer'][" + productIndex + "]/div[@id='details']/a[@data-testid='itemDescription']"));
                actualValue = getTextFromElement(txtDescription).toString();
                expectedValue = "Table";
                match = actualValue.contains(expectedValue);

                if (!match) {
                    System.out.println("Description " + actualValue);
                }
            }
            System.out.println("productCount " + productCount);
        }
    }

    public void addLastProductToCart() throws InterruptedException {
        Thread.sleep(2000);
        clickElement(btnLastProductAddToCart);
        clickElement(btnProductAccessoriesAddToCart);
    }

    public void navigateToCart() throws InterruptedException {
        clickElement(btnViewCart);
        Thread.sleep(2000);
    }

    public void isSearchResultAndTotalProductAMatch() {
        Assert.assertEquals(productCount, totalResults);
    }
}
