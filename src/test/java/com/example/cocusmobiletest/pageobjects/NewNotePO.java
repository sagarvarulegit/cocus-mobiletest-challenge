package com.example.cocusmobiletest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class NewNotePO extends BasePage {
    @FindBy(id = "com.example.android.testing.notes.mock:id/add_note_title")
    public WebElement txtTitle;

    @FindBy(id = "com.example.android.testing.notes.mock:id/add_note_description")
    public WebElement txtNoteDescription; 

    @FindBy(id = "com.example.android.testing.notes.mock:id/fab_add_notes")
    public WebElement btnAddNotes;

    public NewNotePO(AppiumDriver appiumDriver) {
        super(appiumDriver);
        PageFactory.initElements(appiumDriver, this);
    }

    public void enterTitle(String title) {
        enterText(txtTitle, title);
    }

    public void clearTitle() {
        clearText(txtTitle);
    }

    public void enterNoteDescription(String noteDescription) {
        enterText(txtNoteDescription, noteDescription);
    }

    public void addNewNote(String title,String description){
        enterTitle(title);
        enterNoteDescription(description);
        click(btnAddNotes);
    }
}
