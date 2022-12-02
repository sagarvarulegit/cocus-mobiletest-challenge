@sanity @regression @ui
Feature: Verify required UI elements of Note app

Background: I open Note App
Given I open Note App
  Scenario: Verify Dashboard screen
    Given I am at Dashboard
    Then Verify Add button is present and clickable
    And Verify hamburger icon is present and clickable
    And Verify Note label is present

  Scenario: Verify New Note screen
    Given I am at New Note Screen
    Then Verify Title field is present and focused
    And Verify Description field is present
    And Verify Add new note button is present and clickable
    And Verify More options button is present and clickable
    And Verify Back button is present and clickable
    And Verify More options has Add piture button and clickable


  Scenario: Verify Statistics screen
    Given I am at Statistics screen
    Then Verify Statistics screen fields are present
    Then Verify Statistics screen shows "No statistics" message
