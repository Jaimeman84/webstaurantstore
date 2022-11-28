package com.seleniumJavaWebstaurantStore.tests;

import com.seleniumJavaWebstaurantStore.steps.WebstaurantstoreSteps;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;

import java.io.IOException;


@DisplayName("Webstaurant Test")
public class WebstaurantstoreTest extends WebstaurantstoreSteps {

    public WebstaurantstoreTest() throws IOException {
        driver = getDriver();
    }

    @Test
    @DisplayName("WebstaurantStore Search Validation")
    @Description("Perform Search for Specific Product and Validate Results")
    public void webstaurantstoreProductSearchTest() throws IOException, InterruptedException {
        navigateToWebstaurantstore();
        performProductSearch("stainless work table");
        validateProductSearchResults();
        addLastProductToCart();
        navigateToCartPage();
        emptyCart();
        isSearchResultAndTotalProductAMatch();
    }
}
