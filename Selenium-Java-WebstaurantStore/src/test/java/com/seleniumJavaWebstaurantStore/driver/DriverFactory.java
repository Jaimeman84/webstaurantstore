package com.seleniumJavaWebstaurantStore.driver;

import com.github.javafaker.Faker;
import com.seleniumJavaWebstaurantStore.util.PropertyReader;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.time.Duration;

import static com.seleniumJavaWebstaurantStore.util.Helper.getBrowserProperty;
import static com.seleniumJavaWebstaurantStore.util.Helper.getImplicitWait;

public abstract class DriverFactory {

    public static PropertyReader prop;
    public static Faker faker = new Faker();
    protected static WebDriver driver = null;
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() throws IOException {
        if (driver == null) {
            driverThreadLocal.set(getBrowser().getWebDriver());
        }
        driverThreadLocal.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(getImplicitWait()));
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        driverThreadLocal.get().quit();
    }

    private static BrowserType getBrowser() throws IOException {
        String browser = getBrowserProperty();
        switch (browser) {
            case "CHROME":
                return BrowserType.CHROME;
            case "FIREFOX":
                return BrowserType.FIREFOX;
            case "EDGE":
                return BrowserType.EDGE;
            case "SAFARI":
                return BrowserType.SAFARI;
        }
        return BrowserType.CHROME;
    }
}