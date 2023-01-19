package com.shilov.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppiumConfigDataProvider {

    private static final String CONFIG_PROPERTY_PATH = "src/test/resources/appiumconfig.property";

    private static AppiumConfigDataProvider instance;

    private AppiumConfigDataProvider() {
    }

    public static AppiumConfigDataProvider getInstance() {
        if (instance == null) {
            instance = new AppiumConfigDataProvider();
        }
        return instance;
    }

    private static Properties config;

    static {
        config = new Properties();
        try (FileInputStream inputStream = new FileInputStream(CONFIG_PROPERTY_PATH)) {
            config.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAppiumServerUrl() {
        return config.getProperty("server.url");
    }

    public String getPlatformName() {
        return config.getProperty("platformName");
    }

    public String getPlatformVersion() {
        return config.getProperty("platformVersion");
    }

    public String getDeviceName() {
        return config.getProperty("deviceName");
    }

    public String getAppPath() {
        return config.getProperty("app");
    }

    public String getAppPackage() {
        return config.getProperty("app.package");
    }

    public String getAppActivity() {
        return config.getProperty("app.activity");
    }
}
