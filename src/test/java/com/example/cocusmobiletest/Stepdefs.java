package com.example.cocusmobiletest;

import org.junit.Assert;

import com.example.cocusmobiletest.pageobjects.DashBoard;
import com.example.cocusmobiletest.pageobjects.NewNotePO;
import com.example.cocusmobiletest.stepdefinitions.Hooks;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Stepdefs {

    private DashBoard dashBoard;
    private NewNotePO newNote;
    

    public Stepdefs(){
        dashBoard = new DashBoard(Hooks.appiumDriver);
        newNote = new NewNotePO(Hooks.appiumDriver);
    }

    @When("I add Note with {string} and {string}")
    public void I_add_Note_with_and(String title, String description) {
        dashBoard.clickAddButton(title, description);
        newNote.addNewNote(title, description);
    }

    @Then("Verify note is added successfully with {string} and {string}")
    public void Verify_note_is_added_successfully_with_and(String title, String description) {
        Assert.assertTrue("Expected Note with title and description not present", false);
    }
}
