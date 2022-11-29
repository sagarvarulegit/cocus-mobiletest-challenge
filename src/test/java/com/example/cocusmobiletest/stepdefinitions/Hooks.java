package com.example.cocusmobiletest.stepdefinitions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.yaml.snakeyaml.Yaml;

import com.example.cocusmobiletest.config.TestConfig;
import com.example.cocusmobiletest.utils.TestUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.intuit.karate.Runner;

import io.cucumber.java.Scenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class Hooks {
    static AppiumDriverLocalService service = new AppiumServiceBuilder().withArgument(() -> "--base-path", "/wd/hub")
            .usingPort(4723).build();
    public static AppiumDriver appiumDriver;
    public static String scenarioTestData;
    public static TestConfig testConfig;

    @Before
    public void beforeScenario(io.cucumber.java.Scenario scenario)
            throws InterruptedException, JsonParseException, JsonMappingException, IOException {
        // Test Data Setup
        Collection<String> tags = scenario.getSourceTagNames();
        String tagname = tags.stream().filter(x -> x.startsWith("@getDataFromAPI")).findFirst().orElse(null);
        if (tagname != null) {
            TestUtils.getRandomUserDataFromAPI();
            scenarioTestData = Files.readString(Path.of(TestConfig.getInstance().getTestdatahome() + tagname.split("=")[1]));
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        byte[] ss = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(ss, "image/png", scenario.getName());
    }

    @BeforeAll
    public static void setUpTests() throws IOException {
        TestConfig.getInstance();
        setUpAppiumDriver();
    }

    @AfterAll
    public static void tearDownTests() {
        service.stop();
    }

    private static void setUpAppiumDriver() {
        if (service.isRunning()) {
            service.stop();
        }
        String deviceName = getPlatformName();

        if (deviceName.equalsIgnoreCase("android")) {
            setAndroidCapbilites();
        }
        else if (deviceName.equalsIgnoreCase("ios")) {
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

    public static String getPlatformName(){
        if (System.getProperty("platformName") != null) {
            TestConfig.getInstance().setPlatformName(System.getProperty("platformName"));
            return System.getProperty("platformName");
        } else {
            return TestConfig.getInstance().getPlatformName();
        }
    }

    public static void setAndroidCapbilites(){
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
    }

    public static void setIOSCapabilities(){
        String appPath = System.getProperty("user.dir")
                    + testConfig.getApkpath();
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
}
