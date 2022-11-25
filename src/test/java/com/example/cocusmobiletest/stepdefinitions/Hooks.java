package com.example.cocusmobiletest.stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    AppiumDriverLocalService service = new AppiumServiceBuilder().withArgument(() -> "--base-path","/wd/hub").usingPort(4723).build();
    public static AppiumDriver appiumDriver;
    @Before
    public void beforeScenario() throws InterruptedException {
        System.out.println("Before Scenario");
        System.out.println(System.getProperty("user.dir"));
        String apkPath = System.getProperty("user.dir") + "\\src\\test\\resources\\com\\example\\cocusmobiletest\\apk\\app-mock-debug.apk";
        // if (service.isRunning()) {
        //     service.stop();
        // }
        service.stop();
        service.start();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "7b38ef97");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", apkPath);
        capabilities.setCapability("newCommandTimeout ", "300");
        capabilities.setCapability("noReset ", "false");

        AppiumDriver appiumDriver = new AppiumDriver(service, capabilities);
        appiumDriver.getStatus();

        // appiumDriver.findElement(By.id("com.example.android.testing.notes.mock:id/fab_add_notes")).click();
        // Thread.sleep(5000);
        // appiumDriver.findElement(By.id("com.example.android.testing.notes.mock:id/add_note_title")).sendKeys("Test TitleappiumDriver.findElement");
    }

    @After
    public void afterScenario(){
        service.stop();
    }

}
