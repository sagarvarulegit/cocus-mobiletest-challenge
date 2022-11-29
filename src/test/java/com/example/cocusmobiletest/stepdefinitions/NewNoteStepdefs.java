package com.example.cocusmobiletest.stepdefinitions;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import com.example.cocusmobiletest.config.TestConfig;
import com.example.cocusmobiletest.config.TestManager;
import com.example.cocusmobiletest.pageobjects.DashBoardPO;
import com.example.cocusmobiletest.pageobjects.NewNotePO;
import com.example.cocusmobiletest.utils.TestUtils;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class NewNoteStepdefs {
    private String usedTitle;
    private String usedDescription;
    private AppiumDriver  appiumDriver;
    private TestManager testManager;

    public NewNoteStepdefs(TestManager testManager) {
        this.testManager = testManager;
        appiumDriver = Hooks.appiumDriver;
    }

    @When("I add Note with {string} and {string}")
    public void I_add_Note_with_and(String title, String description) {
        testManager.getDashBoardPO().clickAddButton();
        usedTitle = title;
        String type = description.split(":")[0];
        String value = description.split(":")[1];
        switch (type.toLowerCase()) {
            case "text" -> {
                usedDescription = value.trim();
                testManager.getNewNotePO().addNewNote(title, usedDescription);
            }
            case "file" -> {
                usedDescription = TestUtils.readFile(value.trim());
                testManager.getNewNotePO().addNewNote(title, usedDescription);
            }
            default -> {
                usedDescription = description;
                testManager.getNewNotePO().addNewNote(title, description);
            }
        }

    }

    @Then("Verify note is added successfully with {string} and {string}")
    public void Verify_note_is_added_successfully_with_and(String title, String description) {
        if(testManager.getDashBoardPO().isAtDashboard()){
            Assert.assertTrue("Expected Note with title and description not present",
            testManager.getDashBoardPO().verifyNoteAdded(title, description));
        }
       
    }

    @Then("Verify note is added successfully with title and description")
    public void Verify_note_is_added_successfully_with_title_and_description() {
        Assert.assertTrue("Expected Note with title and description not present",
                testManager.getDashBoardPO().verifyNoteAdded(usedTitle, usedDescription));
    }

    @When("I add Note with title and description")
    public void I_add_Note_with_title_and_description() {
        String json = Hooks.scenarioTestData;
        ReadContext ctx = JsonPath.parse(json);
        usedTitle = ctx.read("$.results[0].name.title") + " " + ctx.read("$.results[0].name.first") + " "
                + ctx.read("$.results[0].name.last");
        String strDate = ctx.read("$.results[0].registered.date");
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(strDate);
        usedDescription = zonedDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME);
        testManager.getDashBoardPO().clickAddButton();
        testManager.getNewNotePO().addNewNote(usedTitle, usedDescription);
    }

    @When("I click on Add Image for new note")
    public void I_click_on_Add_Image_for_new_note() {
        testManager.getDashBoardPO().clickAddButton();
        testManager.getNewNotePO().clickAddImage();
    }

    @When("I add Note following notes")
    public void I_add_Note_following_notes(DataTable dt) {
        dt.asLists().forEach(row -> {
            testManager.getDashBoardPO().clickAddButton();
            testManager.getNewNotePO().addNewNote(row.get(0), row.get(1));

        });
    }

    @Then("Verify Note count is {string}")
    public void Verify_Note_count_is(String count) {
        if (testManager.getDashBoardPO().isAtDashboard()) {
            Assert.assertEquals("Note count is not as expected", Integer.parseInt(count), testManager.getDashBoardPO().getNoteCount());
        }
    }

    @Given("I am at dashboard")
    public void I_am_at_dashboard() {
        Assert.assertTrue(testManager.getDashBoardPO().isAtDashboard());
    }

    @When("I restart app")
    public void I_restart_app() {
        if(TestConfig.getInstance().getPlatformName().equalsIgnoreCase("android")) {
            AndroidDriver androidDriver = (AndroidDriver)appiumDriver;
            String packageName = androidDriver.getCurrentPackage();
            androidDriver.terminateApp(packageName);
            androidDriver.activateApp(packageName);
        }
    }

    @Given("I compare image")
    public void I_compare_image() {
        TestUtils.compareImages();
    }

    @When("I add blank Note")
    public void I_add_blank_Note() {
        testManager.getDashBoardPO().clickAddButton();
        testManager.getNewNotePO().addNewBlankNote();
    }

    @Then("Verify Blank note is note saved")
    public void Verify_Blank_note_is_note_saved() {
        if(testManager.getDashBoardPO().isAtDashboard()){
            Assert.assertFalse(testManager.getDashBoardPO().isBlankNotePresent());
        }
        
    }
}
