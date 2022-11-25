package com.example.cocusmobiletest;

import com.example.cocusmobiletest.pageobjects.DashBoard;
import com.example.cocusmobiletest.stepdefinitions.Hooks;

import io.cucumber.java.en.Given;

public class Stepdefs {
    @Given("I click on Add button")
    public void I_click_on_Add_button() {
        
        DashBoard dashBoard = new DashBoard(Hooks.appiumDriver);
        dashBoard.addNewNote("Title", "Descpt");
    }
}
