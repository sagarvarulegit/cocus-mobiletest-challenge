package com.example.cocusmobiletest.stepdefinitions;

import org.junit.Assert;
import org.openqa.selenium.ScreenOrientation;

import com.example.cocusmobiletest.config.DriverManager;
import com.example.cocusmobiletest.config.TestConfig;
import com.example.cocusmobiletest.config.TestManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.*;

public class UIStepdefs {
    private AppiumDriver  appiumDriver;
    private TestManager testManager;
    
    public UIStepdefs(TestManager testManager) {
        this.appiumDriver = DriverManager.appiumDriver;
        this.testManager = testManager;
    }

    @Given("I am at Dashboard")
    public void I_am_at_Dashboard() {
       Assert.assertTrue(testManager.getDashBoardPO().isAtDashboard());
    }

    @Then("Verify Add button is present and clickable")
    public void Verify_Add_button_is_present_and_clickable() {
        Assert.assertTrue(testManager.getDashBoardPO().isAddButtonPresent());
    }

    @Then("Verify hamburger icon is present and clickable")
    public void Verify_hamburger_icon_is_present_and_clickable() {
        Assert.assertTrue(testManager.getDashBoardPO().isHamburgerClickable());
    }

    @Then("Verify Note label is present")
    public void Verify_Note_label_is_present() {
        Assert.assertTrue(testManager.getDashBoardPO().isNoteLabelPresent());
    }

    @Given("I am at New Note Screen")
    public void I_am_at_New_Note_Screen() {
        testManager.getDashBoardPO().clickAddButton();

    }

    @Then("Verify Description field is present")
    public void Verify_Description_field_is_present() {
       Assert.assertTrue(testManager.getNewNotePO().isDescriptionFieldPresent());
    }

    @Then("Verify Title field is present and focused")
    public void Verify_Title_field_is_present_and_focused() {
       Assert.assertTrue(testManager.getNewNotePO().isTitleFieldPresent());
    }

    @Then("Verify Add new note button is present and clickable")
    public void Verify_Add_new_note_button_is_present_and_clickable() {
        Assert.assertTrue(testManager.getNewNotePO().isAddNewNotePresent());
    }

    @Then("Verify More options button is present and clickable")
    public void Verify_More_options_button_is_present_and_clickable() {
        Assert.assertTrue(testManager.getNewNotePO().isMoreOptionsPresent());
    }

    @Then("Verify More options has Add piture button and clickable")
    public void Verify_More_options_has_Add_piture_button_and_clickable() {
        Assert.assertTrue(testManager.getNewNotePO().isAddPicturePresent());
    }

    @Then("Verify Back button is present and clickable")
    public void Verify_Back_button_is_present_and_clickable() {
        Assert.assertTrue(testManager.getNewNotePO().isBackButtonPresent());
    }

    @Given("I am at Statistics screen")
    public void I_am_at_Statistics_screen() {
       testManager.getDashBoardPO().goToStatistics();
    }

    @Then("Verify Statistics screen fields are present")
    public void Verify_Statistics_screen_fields_are_present() {
        Assert.assertTrue(testManager.getStatisticsPO().verifyStatisticsPage());
    }

    @Then("Verify Statistics screen shows {string} message")
    public void Verify_Statistics_screen_shows_message(String msg) {
        Assert.assertEquals(msg, testManager.getStatisticsPO().getNoStatisticsText());
       
    }

    @Given("I open Note App")
    public void I_open_Note_App() {
        if(TestConfig.getInstance().getPlatformName().equalsIgnoreCase("android")) {
            AndroidDriver androidDriver = (AndroidDriver)appiumDriver;
            String packageName = androidDriver.getCurrentPackage();
            androidDriver.terminateApp(packageName);
            androidDriver.activateApp(packageName);
            androidDriver.rotate(ScreenOrientation.PORTRAIT);
        }
    }
}
