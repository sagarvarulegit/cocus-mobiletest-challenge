package com.example.cocusmobiletest.stepdefinitions;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;

import com.example.cocusmobiletest.pageobjects.DashBoard;
import com.example.cocusmobiletest.pageobjects.NewNotePO;
import com.example.cocusmobiletest.utils.TestUtils;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NewNoteStepdefs {

    private DashBoard dashBoard;
    private NewNotePO newNote;
    private String usedTitle;
    private String usedDescription;

    public NewNoteStepdefs() {
        dashBoard = new DashBoard(Hooks.appiumDriver);
        newNote = new NewNotePO(Hooks.appiumDriver);
    }

    @When("I add Note with {string} and {string}")
    public void I_add_Note_with_and(String title, String description) {
        dashBoard.clickAddButton();
        usedTitle = title;
        String type = description.split(":")[0];
        String value = description.split(":")[1];
        switch (type.toLowerCase()) {
            case "text" -> {
                usedDescription = value.trim();
                newNote.addNewNote(title, usedDescription);
            }
            case "file" -> {
                usedDescription = TestUtils.readFile( value.trim());
                newNote.addNewNote(title, usedDescription);
            }
            default -> {
                usedDescription = description;
                newNote.addNewNote(title, description);
            }
        }
    }

    @Then("Verify note is added successfully with {string} and {string}")
    public void Verify_note_is_added_successfully_with_and(String title, String description) {
        Assert.assertTrue("Expected Note with title and description not present",
                dashBoard.verifyNoteAdded(title, description));
    }

    @Then("Verify note is added successfully with title and description")
    public void Verify_note_is_added_successfully_with_title_and_description() {
        Assert.assertTrue("Expected Note with title and description not present",
                dashBoard.verifyNoteAdded(usedTitle, usedDescription));
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
        dashBoard.clickAddButton();
        newNote.addNewNote(usedTitle, usedDescription);
    }

    @When("I click on Add Image for new note")
    public void I_click_on_Add_Image_for_new_note() {
        dashBoard.clickAddButton();
        newNote.clickAddImage();
    }

    @When("I add Note following notes")
    public void I_add_Note_following_notes(DataTable dt) {
        dt.asLists().forEach(row -> {
            dashBoard.clickAddButton();
            newNote.addNewNote(row.get(0), row.get(1));
        });
    }
}
