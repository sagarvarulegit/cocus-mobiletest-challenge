package com.example.cocusmobiletest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;

public class DashBoard extends BasePage {

    @FindBy(id = "com.example.android.testing.notes.mock:id/fab_add_notes")
    public WebElement btnAddNote;

    @FindBy(id = "com.example.android.testing.notes.mock:id/note_detail_title")
    public List<WebElement> listNotesTitle;

    @FindBy(id = "com.example.android.testing.notes.mock:id/note_detail_description")
    public List<WebElement> listNotesDescription;

    public DashBoard(AppiumDriver appiumDriver) {
        super(appiumDriver);
        PageFactory.initElements(appiumDriver, this);
    }

    public void clickAddButton() {
        click(btnAddNote);
    }

    public boolean verifyNoteAdded(String title, String description) {

        boolean btitle = listNotesTitle.stream().anyMatch(x -> x.getText().equals(title));
        boolean bdescription = listNotesDescription.stream().anyMatch(x -> x.getText().equals(description));
        if (listNotesTitle.stream().anyMatch(x -> x.getText().equals(title)) &&
                listNotesDescription.stream().anyMatch(x -> x.getText().equals(description))) {
            return true;
        } else {
            return false;
        }
    }

}
