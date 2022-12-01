package com.example.cocusmobiletest.stepdefinitions;

import com.example.cocusmobiletest.config.TestManager;

import io.appium.java_client.AppiumDriver;

public class UIStepdefs {
    private AppiumDriver  appiumDriver;
    private TestManager testManager;
    
    public UIStepdefs(TestManager testManager) {
        this.appiumDriver = appiumDriver;
        this.testManager = testManager;
    }

    
}
