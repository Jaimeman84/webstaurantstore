package com.seleniumJavaWebstaurantStore.util;

import com.seleniumJavaWebstaurantStore.driver.DriverManager;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import static com.seleniumJavaWebstaurantStore.util.Helper.takeScreenshot;

public class Watcher extends DriverManager implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        takeScreenshot(driver, extensionContext.getDisplayName());
    }
}
