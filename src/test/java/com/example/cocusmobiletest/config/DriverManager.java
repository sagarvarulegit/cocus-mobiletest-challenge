package com.example.cocusmobiletest.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class DriverManager {
    static AppiumDriverLocalService service;
    public static AppiumDriver appiumDriver;


    public static void setUpLocalAppiumDriver() {
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
        capabilities.setCapability("newCommandTimeout ", "60");
        capabilities.setCapability("noReset ", "false");
        capabilities.setCapability("disableWindowAnimation ", "true");

        appiumDriver = new AndroidDriver(service, capabilities);

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
        capabilities.setCapability("newCommandTimeout ", "60");
        capabilities.setCapability("noReset ", "false");
        appiumDriver = new IOSDriver(service, capabilities);

    }

    public static void stopAppiumService() {
        appiumDriver.quit();
        service.stop();
    }

    public static void setUpBrowserStack() throws MalformedURLException, FileNotFoundException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/config/parallel.conf.json"));
        JSONArray envs = (JSONArray) config.get("environments");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(0);
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(capabilities.getCapability(pair.getKey().toString()) == null){
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }
        }

        String username = System.getenv("BROWSERSTACK_USERNAME");
        if(username == null) {
            username = (String) config.get("username");
        }
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if(accessKey == null) {
            accessKey = (String) config.get("access_key");
        }
        String app = System.getenv("BROWSERSTACK_APP_ID");
        if(app != null && !app.isEmpty()) {
            capabilities.setCapability("app", app);
        }

        capabilities.setCapability("platformName", getPlatformName());
        capabilities.setCapability("deviceName", getDeviceName());
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("platformVersion", getPlatformVersion());
        capabilities.setCapability("app", "CocusNoteAppSV");
        capabilities.setCapability("newCommandTimeout ", "60");
        capabilities.setCapability("noReset ", "false");
        capabilities.setCapability("project ", "SV_Test_Cocus");
        capabilities.setCapability("build ", "SV_Test_Cocus-1");
        capabilities.setCapability("name ", "first_run");
        capabilities.setCapability("browserstack.debug ", "true");
        capabilities.setCapability("disableWindowAnimation ", "true");


        appiumDriver =new AndroidDriver(new URL("http://" + username + ":" + accessKey + "@" +config.get("server") + "/wd/hub"), capabilities);
    }

    private static String getPlatformVersion() {
        if (System.getProperty("platformVersion") != null) {
            TestConfig.getInstance().setPlatformVersion(System.getProperty("platformVersion"));
            return System.getProperty("platformVersion");
        } else {
            return TestConfig.getInstance().getPlatformVersion();
        }
    }
}