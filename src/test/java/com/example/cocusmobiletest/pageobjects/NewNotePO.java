package com.example.cocusmobiletest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class NewNotePO extends BasePage {

    /*Version 8+ of Appium insists to use WebElement instead of MobileElement as mentioned in migration guide
    https://github.com/appium/java-client/blob/master/docs/v7-to-v8-migration-guide.md#mobileelement */
    
    @FindBy(id = "com.example.android.testing.notes.mock:id/add_note_title")
    public WebElement txtTitle;

    @FindBy(id = "com.example.android.testing.notes.mock:id/add_note_description")
    public WebElement txtNoteDescription; 

    @FindBy(id = "com.example.android.testing.notes.mock:id/fab_add_notes")
    public WebElement btnAddNotes;

    @FindBy(id="com.example.android.testing.notes.mock:id/title")
    private WebElement btnAddPicture;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    private WebElement btnMoreOptions;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    private WebElement btnBackButton;

    private AppiumDriver appiumDriver;

    public NewNotePO(AppiumDriver appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        // this.btnBackButton = appiumDriver.findElement(AppiumBy.accessibilityId("Navigate up"));
        // this.btnMoreOptions = appiumDriver.findElement(AppiumBy.accessibilityId("More options"));
        PageFactory.initElements(appiumDriver, this);
        // PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
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

    public void addNewBlankNote(){
        enterTitle("");
        enterBlankNoteDescription();
        click(btnAddNotes);
    }

    private void enterBlankNoteDescription() {
        enterKeys(txtNoteDescription, "ENTER");
    }

    public void clickAddImage() {
        click(txtNoteDescription);
        click(appiumDriver.findElement(AppiumBy.accessibilityId("More options")));
        click(btnAddPicture);
    }

    public boolean isTitleFieldPresent() {
        return isElementVisible(txtTitle);
    }

    public boolean isDescriptionFieldPresent() {
        return isElementVisible(txtNoteDescription);
    }

    public boolean isAddNewNotePresent() {
        return isElementClickable(btnAddNotes);
    }

    public boolean isMoreOptionsPresent() {
        return isElementClickable(btnMoreOptions);
    }

    public boolean isAddPicturePresent() {
        click(btnMoreOptions);
        return isElementClickable(btnAddPicture);
    }

    public boolean isBackButtonPresent() {
        return isElementClickable(btnBackButton);
    }


}
