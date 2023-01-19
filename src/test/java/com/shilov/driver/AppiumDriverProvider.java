package com.shilov.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.*;

public class AppiumDriverProvider {

    private AppiumDriver driver;

    private static AppiumDriverProvider instance;

    private AppiumDriverProvider() {
    }

    public static AppiumDriverProvider getInstance() {
        if (instance == null) {
            instance = new AppiumDriverProvider();
        }
        return instance;
    }

    private AppiumConfigDataProvider configDataProvider = AppiumConfigDataProvider.getInstance();

    public AppiumDriver getAndroidDriver() {
        if (driver == null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(DEVICE_NAME, configDataProvider.getDeviceName());
            capabilities.setCapability(PLATFORM_NAME, configDataProvider.getPlatformName());
            capabilities.setCapability(PLATFORM_VERSION, configDataProvider.getPlatformVersion());
            capabilities.setCapability(APP, new File(configDataProvider.getAppPath()).getAbsolutePath());
            capabilities.setCapability("appActivity", configDataProvider.getAppActivity());
            capabilities.setCapability("appPackage", configDataProvider.getAppPackage());
            try {
                driver = new AndroidDriver(new URL(configDataProvider.getAppiumServerUrl()), capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
