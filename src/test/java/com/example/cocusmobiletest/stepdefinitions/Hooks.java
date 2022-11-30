package com.example.cocusmobiletest.stepdefinitions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.yaml.snakeyaml.Yaml;

import com.example.cocusmobiletest.config.DriverManager;
import com.example.cocusmobiletest.config.TestConfig;
import com.example.cocusmobiletest.utils.TestUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.ImmutableMap;
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
            scenarioTestData = Files
                    .readString(Path.of(TestConfig.getInstance().getTestdatahome() + tagname.split("=")[1]));
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        byte[] ss = ((TakesScreenshot) DriverManager.appiumDriver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(ss, "image/png", scenario.getName());
    }

    @BeforeAll
    public static void setUpTests() throws IOException {
        TestConfig.getInstance();
        DriverManager.setUpAppiumDriver();
    }

    @AfterAll
    public static void tearDownTests() {
        DriverManager.stopAppiumService();
    }

   
}
