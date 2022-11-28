package com.seleniumJavaWebstaurantStore.util;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.seleniumJavaWebstaurantStore.util.PropKey.*;

public class Helper {

    public static long getImplicitWait() throws IOException {
        return Long.parseLong(PropertyReader.getInstance().getProperty(ImplicitWait.getPropValue()));
    }

    public static long getExplicitWait() throws IOException {
        return Long.parseLong(PropertyReader.getInstance().getProperty(ExplicitWait.getPropValue()));
    }

    public static String getBrowserProperty() throws IOException {

        List<String> supportedBrowsers = Arrays.asList("CHROME", "FIREFOX", "EDGE", "SAFARI");
        String browserPropertyFile = PropertyReader.getInstance().getProperty(DefaultBrowser.getPropValue());
        String browserSystemVariable = System.getProperty("browser");

        if(supportedBrowsers.contains(browserSystemVariable.toUpperCase()))
            return browserSystemVariable.toUpperCase();
        else
            return browserPropertyFile.toUpperCase();
    }

    @Attachment(value = "{name}", type = "image/png")
    public static synchronized byte[] takeScreenshot(WebDriver driver, String name) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
