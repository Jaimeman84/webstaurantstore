package com.seleniumJavaWebstaurantStore.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariDriver;

public enum BrowserType implements DriverSetup {

    CHROME{
        @Override
        public Capabilities getBrowserCapabilities() {
            return getBrowserOptions();
        }

        @Override
        public WebDriver getWebDriver() {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(getBrowserOptions());
        }

        @Override
        public ChromeOptions getBrowserOptions() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.setAcceptInsecureCerts(true);
            return chromeOptions;
        }
    },

    FIREFOX{
        @Override
        public Capabilities getBrowserCapabilities() {
            return getBrowserOptions();
        }

        @Override
        public WebDriver getWebDriver() {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(getBrowserOptions());
        }

        @SuppressWarnings("deprecation")
        @Override
        public FirefoxOptions getBrowserOptions() {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            FirefoxProfile firefoxProfile = new FirefoxProfile();

            firefoxProfile.setAcceptUntrustedCertificates(true);
            firefoxProfile.setAssumeUntrustedCertificateIssuer(true);

            firefoxOptions.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
            return firefoxOptions;
        }
    },

    EDGE{
        @Override
        public Capabilities getBrowserCapabilities() {
            return null;
        }

        @Override
        public WebDriver getWebDriver() {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        }

        @Override
        public Object getBrowserOptions() {
            return null;
        }
    },

    SAFARI{
        @Override
        public Capabilities getBrowserCapabilities() {
            return null;
        }

        @Override
        public WebDriver getWebDriver() {
            return new SafariDriver();
        }

        @Override
        public Object getBrowserOptions() {
            return null;
        }
    };
}
