package com.example.cocusmobiletest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class DashBoard extends BasePage {

    @FindBy(id = "com.example.android.testing.notes.mock:id/fab_add_notes")
    public WebElement btnAddNote;

    @FindBy(id = "com.example.android.testing.notes.mock:id/note_detail_title")
    public List<WebElement> listNotesTitle;

    @FindBy(id = "com.example.android.testing.notes.mock:id/note_detail_description")
    public List<WebElement> listNotesDescription;

    @FindBy(id = "com.example.android.testing.notes.mock:id/design_menu_item_text")
    public WebElement btnStatistic;

    @FindBy(id = "com.example.android.testing.notes.mock:id/no_statistics")
    public WebElement txtNoStatistics;

    private AppiumDriver appiumDriver;

    public DashBoard(AppiumDriver appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
    }

    public void clickAddButton() {
        click(btnAddNote);
    }

    public boolean verifyNoteAdded(String title, String description) {
        if (listNotesTitle.stream().anyMatch(x -> x.getText().equals(title)) &&
                listNotesDescription.stream().anyMatch(x -> x.getText().equals(description))) {
            return true;
        } else {
            return false;
        }
    }

    public void goToStatistics() {
        //Version 8 Appium only supports accessibility id only via AppiumBy and AppiumBy cannot be used in PageFactory
        click(appiumDriver.findElement(AppiumBy.accessibilityId("Navigate up")));
        click(btnStatistic);
    }

    public boolean verifyAtStatistics() {
        if (txtNoStatistics.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

}
