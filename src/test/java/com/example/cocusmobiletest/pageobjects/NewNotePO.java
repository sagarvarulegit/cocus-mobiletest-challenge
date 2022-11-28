package com.example.cocusmobiletest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;

public class NewNotePO extends BasePage {

    /*Version 8+ of Appium insists to use WebElement instead of MobileElement as mentioned in migration guide
    https://github.com/appium/java-client/blob/master/docs/v7-to-v8-migration-guide.md#mobileelement */
    
    @FindBy(id = "com.example.android.testing.notes.mock:id/add_note_title")
    public WebElement txtTitle;

    @FindBy(id = "com.example.android.testing.notes.mock:id/add_note_description")
    public WebElement txtNoteDescription; 

    @FindBy(id = "com.example.android.testing.notes.mock:id/fab_add_notes")
    public WebElement btnAddNotes;


    //Version 8 Appium only supports accessibility id only via AppiumBy and AppiumBy cannot be used in PageFactory
    //So we need cannot use the below approach
    // @AndroidBy(accessibility = "More options")
    // @iOSXCUITBy(accessibility = "More options")
    // private WebElement btnMoreOptions;

    @FindBy(id="com.example.android.testing.notes.mock:id/title")
    private WebElement btnAddPicture;

    private AppiumDriver appiumDriver;

    public NewNotePO(AppiumDriver appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
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

    public void clickAddImage() {
        click(txtNoteDescription);
        click(appiumDriver.findElement(AppiumBy.accessibilityId("More options")));
        click(btnAddPicture);
    }
}
