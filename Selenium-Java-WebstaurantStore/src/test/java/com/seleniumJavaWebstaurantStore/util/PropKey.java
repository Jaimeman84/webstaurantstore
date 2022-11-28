package com.seleniumJavaWebstaurantStore.util;

public enum PropKey {

    WebstaurantstoreUrl("GoogleURL"),
    DefaultBrowser("DefaultBrowser"),
    ImplicitWait("ImplicitWait"),
    ExplicitWait("ExplicitWait");

    private final String propValue;

    PropKey(String propValue) {
        this.propValue = propValue;
    }
    public String getPropValue() {
        return propValue;
    }
}
