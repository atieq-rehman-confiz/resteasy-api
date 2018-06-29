package com.confiz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public final class PropertiesConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesConfig.class);
    private PropertiesConfig(){
    }

    private static Properties PROPERTIES = new Properties();

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("application.properties");

        try {
            PROPERTIES.load(input);
        }
        catch (Exception e){
            LOGGER.error("Unable to load PROPERTIES using application.PROPERTIES file", e);
        }
    }
    public static String getProperty(String key) {
        if(key == null || key.isEmpty() || PROPERTIES == null || PROPERTIES.isEmpty())
            return "";

        return PROPERTIES.getProperty(key);
    }

}
