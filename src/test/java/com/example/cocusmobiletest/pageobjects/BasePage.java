package com.example.cocusmobiletest.pageobjects;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;


public class BasePage {
    WebDriverWait wait;

    public BasePage(AppiumDriver appiumDriver){
        wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(5));
    }
    public void click(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void enterText(WebElement element, String text){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys("");
        element.sendKeys(text);
    }

    public void enterKeys(WebElement element, String keyString){
        if (keyString != null && !keyString.isEmpty()) {
           if (keyString.equalsIgnoreCase("enter")){
                element.sendKeys("\n");
           }
        }
    }

    protected void clearText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys("");
    }

    protected boolean elementVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean waitElementNotPresent(WebElement element) {
        try {
            wait.until(ExpectedConditions.stalenessOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
