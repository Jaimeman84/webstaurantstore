package com.seleniumJavaWebstaurantStore.steps;

import com.seleniumJavaWebstaurantStore.driver.DriverManager;
import com.seleniumJavaWebstaurantStore.pages.WebstaurantstoreHomePage;
import com.seleniumJavaWebstaurantStore.pages.WebstaurantstoreCartPage;

import io.qameta.allure.Step;

import java.io.IOException;

public class WebstaurantstoreSteps extends DriverManager {

    private final WebstaurantstoreHomePage homePage = new WebstaurantstoreHomePage(getDriver());
    private final WebstaurantstoreCartPage cartPage = new WebstaurantstoreCartPage(getDriver());

    public WebstaurantstoreSteps() throws IOException {
        driver = getDriver();
    }

    @Step("Navigate to Webstaurantstore")
    public void navigateToWebstaurantstore() throws IOException {
        homePage.navigateWebstaurantstore();
    }

    @Step("Perform Webstaurantstore Product Search {0}")
    public void performProductSearch(String keyword) {
        homePage.performProductSearch(keyword);
    }

    @Step("Validate Webstaurantstore Product Search Result")
    public void validateProductSearchResults() {
        homePage.validateResultsText();
    }

    @Step("Add Last Product To Cart")
    public void addLastProductToCart() throws InterruptedException {
        homePage.addLastProductToCart();
    }

    @Step("Add Last Product To Cart")
    public void navigateToCartPage() throws InterruptedException {
        homePage.navigateToCart();
        cartPage.validateProductInTheCartIsCorrect();
    }
    @Step("Empty Cart")
    public void emptyCart() throws IOException, InterruptedException {
        cartPage.removeCartItem();
        cartPage.validateCartIsEmpty();
    }

    @Step("Empty Cart")
    public void isSearchResultAndTotalProductAMatch() throws IOException {
        homePage.isSearchResultAndTotalProductAMatch();
    }

}
