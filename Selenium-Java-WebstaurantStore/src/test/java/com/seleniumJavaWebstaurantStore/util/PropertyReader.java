package com.seleniumJavaWebstaurantStore.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static volatile PropertyReader propInstance;

    public static synchronized PropertyReader getInstance() {
        if(propInstance == null)
            propInstance = new PropertyReader();
        return propInstance;
    }

    /**
     * Method to return a property value
     * @param propertyName Name of the property
     * @return Property value
     */
    public String getProperty(String propertyName) throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
        prop.load(inputStream);
        if(prop.getProperty(propertyName)!=null) {
            return prop.getProperty(propertyName);
        }
        return null;
    }
}
