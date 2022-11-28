package com.seleniumJavaWebstaurantStore.driver;

import com.seleniumJavaWebstaurantStore.util.PropertyReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;

public class DriverManager extends DriverFactory {

    @BeforeAll
    public static void driverSetup() throws IOException {
        prop = PropertyReader.getInstance();
        driver = getDriver();
    }

    @BeforeEach
    public void cleanUp() throws IOException {
        getDriver().manage().deleteAllCookies();
    }

    @AfterAll
    public static void tearDown() {
        driver = null;
        quitDriver();
    }
}
