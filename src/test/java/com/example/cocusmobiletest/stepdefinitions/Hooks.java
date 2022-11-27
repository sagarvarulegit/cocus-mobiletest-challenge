package com.example.cocusmobiletest.stepdefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.yaml.snakeyaml.Yaml;

import com.example.cocusmobiletest.apitests.RandomUserRunner;
import com.example.cocusmobiletest.utils.TestSuite;
import com.example.cocusmobiletest.utils.TestUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.karate.Runner;
import com.intuit.karate.core.Scenario;
import com.intuit.karate.junit5.Karate;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    AppiumDriverLocalService service = new AppiumServiceBuilder().withArgument(() -> "--base-path", "/wd/hub")
            .usingPort(4723).build();
    public static AppiumDriver appiumDriver;
    public static String scenarioTestData;

    @Before
    public void beforeScenario(io.cucumber.java.Scenario scenario)
            throws InterruptedException, JsonParseException, JsonMappingException, IOException {
        System.out.println("Before Scenario");
        System.out.println(System.getProperty("user.dir"));
        String apkPath = System.getProperty("user.dir")
                + "\\src\\test\\resources\\com\\example\\cocusmobiletest\\apk\\app-mock-debug.apk";
        // if (service.isRunning()) {
        // service.stop();
        // }
        service.stop();
        service.start();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "7b38ef97");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", apkPath);
        capabilities.setCapability("newCommandTimeout ", "30000000");
        capabilities.setCapability("noReset ", "false");

        appiumDriver = new AppiumDriver(service, capabilities);
        appiumDriver.getStatus();

        // Test Data Setup
        Yaml yaml = new Yaml();
        Collection<String> tags = scenario.getSourceTagNames();
        String tagname = tags.stream().filter(x -> x.startsWith("@getDataFromAPI")).findFirst().orElse(null);
        if (tagname != null) {
            Map<String, Object> args = new HashMap();
            args.put("tags", "@get-user");
            Map<String, Object> result = Runner.runFeature(getClass(),
                    "classpath:RandomUser.feature", args, true);

            // TestUtils.getRandomUserDataFromAPI();
            scenarioTestData = Files.readString(Path.of(TestSuite.TEST_DATA_HOME + tagname.split("=")[1]));

        }
    }

    @After
    public void afterScenario() {
        service.stop();
    }

}
