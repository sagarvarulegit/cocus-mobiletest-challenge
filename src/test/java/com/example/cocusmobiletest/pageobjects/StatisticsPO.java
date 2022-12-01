package com.example.cocusmobiletest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class StatisticsPO extends BasePage {

    private AppiumDriver appiumDriver;

    @FindBy(id="com.example.android.testing.notes.mock:id/no_statistics")
    private WebElement lblNoStatistics;

    @FindBy(xpath="//android.widget.TextView[@text='Statistics']")
    private WebElement headerStatistics;

    @FindBy(id="com.example.android.testing.notes.mock:id/no_statistics_placeholder")
    private WebElement imgNoStatistics;

    public StatisticsPO(AppiumDriver appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
    }

    public boolean verifyStatisticsPage() {
        if (isElementVisible(headerStatistics) && isElementVisible(imgNoStatistics) && isElementVisible(lblNoStatistics)) {
            return true;
        }
        return false;
    }

public String getNoStatisticsText() {
        return getElementText(lblNoStatistics);
    }


    
}
