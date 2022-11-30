package com.example.cocusmobiletest.stepdefinitions;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.example.cocusmobiletest.config.DriverManager;
import com.example.cocusmobiletest.config.TestConfig;
import com.example.cocusmobiletest.utils.TestUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class Hooks {

    public static String scenarioTestData;
    public static TestConfig testConfig;

    @BeforeAll
    public static void setUpTests() throws IOException {
        TestConfig.getInstance();
        if (System.getProperty("shouldRunOnBrowserStack") != null
                && System.getProperty("shouldRunOnBrowserStack").equalsIgnoreCase("true")) {

            TestConfig.getInstance().setShouldRunOnBrowserStack(System.getProperty("shouldRunOnBrowserStack"));
            DriverManager.setUpBrowserStack();

        } else {
            DriverManager.setUpLocalAppiumDriver();
        }

    }

    @AfterAll
    public static void tearDownTests() {
        DriverManager.stopAppiumService();
    }

    @Before
    public void beforeScenario(io.cucumber.java.Scenario scenario)
            throws InterruptedException, JsonParseException, JsonMappingException, IOException {
        // Test Data Setup
        Collection<String> tags = scenario.getSourceTagNames();
        String tagname = tags.stream().filter(x -> x.startsWith("@getDataFromAPI")).findFirst().orElse(null);
        if (tagname != null) {
            TestUtils.getRandomUserDataFromAPI();
            scenarioTestData = Files
                    .readString(Path.of(TestConfig.getInstance().getTestdatahome() + tagname.split("=")[1]),
                            Charset.forName("UTF-8"));
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        byte[] ss = ((TakesScreenshot) DriverManager.appiumDriver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(ss, "image/png", scenario.getName());
    }
}
