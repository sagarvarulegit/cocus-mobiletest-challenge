package com.example.cocusmobiletest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DashBoard extends BasePage {
    

@FindBy(id="com.example.android.testing.notes.mock:id/fab_add_notes")
public WebElement btnAddNote ;

public DashBoard(AppiumDriver appiumDriver){
    super(appiumDriver);
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public void addNewNote(String title,String description){
        click(btnAddNote);
    }
        
}
