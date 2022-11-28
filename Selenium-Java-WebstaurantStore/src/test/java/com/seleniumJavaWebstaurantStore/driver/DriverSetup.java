package com.seleniumJavaWebstaurantStore.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public interface DriverSetup {
    Capabilities getBrowserCapabilities();
    WebDriver getWebDriver();
    Object getBrowserOptions();
}
