package com.example.cocusmobiletest.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class DriverManager {
    static AppiumDriverLocalService service;
    public static AppiumDriver appiumDriver;


    public static void setUpAppiumDriver() {
         service = new AppiumServiceBuilder()
            .withArgument(() -> "--relaxed-security", "")
            .withArgument(() -> "--base-path", "/wd/hub")
            .usingPort(4723).build();
        if (service.isRunning()) {
            service.stop();
        }
        String deviceName = getPlatformName();

        if (deviceName.equalsIgnoreCase("android")) {
            setAndroidCapbilites();
        } else if (deviceName.equalsIgnoreCase("ios")) {
            setIOSCapabilities();
        }
    }

    public static String getDeviceName() {
        if (System.getProperty("deviceName") != null) {
            TestConfig.getInstance().setDevicename(System.getProperty("deviceName"));
            return System.getProperty("deviceName");
        } else {
            return TestConfig.getInstance().getDevicename();
        }
    }

    public static String getPlatformName() {
        if (System.getProperty("platformName") != null) {
            TestConfig.getInstance().setPlatformName(System.getProperty("platformName"));
            return System.getProperty("platformName");
        } else {
            return TestConfig.getInstance().getPlatformName();
        }
    }

    public static void setAndroidCapbilites() {
        String apkPath = System.getProperty("user.dir")
                + TestConfig.getInstance().getApkpath();
        service.start();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", getPlatformName());
        capabilities.setCapability("deviceName", getDeviceName());
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", apkPath);
        capabilities.setCapability("newCommandTimeout ", "30000000");
        capabilities.setCapability("noReset ", "false");

        appiumDriver = new AndroidDriver(service, capabilities);

        Map<String, Object> argv1 = new HashMap<>();
        argv1.put("command", "settings");
        argv1.put("args", List.of("put", "global", "window_animation_scale ", "0"));
        String result1 = appiumDriver.executeScript("mobile: shell", argv1).toString();

        Map<String, Object> argv2 = new HashMap<>();
        argv2.put("command", "settings");
        argv2.put("args", List.of("put", "global", "transition_animation_scale", "0"));
        String result2 = appiumDriver.executeScript("mobile: shell", argv2).toString();

        Map<String, Object> argv3 = new HashMap<>();
        argv3.put("command", "settings");
        argv3.put("args", List.of("put", "global", "animator_duration_scale", "0"));
        String result3 = appiumDriver.executeScript("mobile: shell", argv3).toString();
    }

    public static void setIOSCapabilities() {
        String appPath = System.getProperty("user.dir")
                + TestConfig.getInstance().getApkpath();
        service.start();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", getPlatformName());
        capabilities.setCapability("deviceName", getDeviceName());
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", appPath);
        capabilities.setCapability("newCommandTimeout ", "30000000");
        capabilities.setCapability("noReset ", "false");
        appiumDriver = new IOSDriver(service, capabilities);

    }

    public static void stopAppiumService() {
        service.stop();
    }
}
