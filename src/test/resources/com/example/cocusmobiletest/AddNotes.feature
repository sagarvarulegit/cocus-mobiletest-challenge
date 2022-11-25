Feature: Add Notes

  Scenario Outline: Add Note
    When I add Note with "<title>" and "<description>"
    Then Verify note is added successfully with "<title>" and "<description>"

    Examples: 
      | title        | description        |
      | testABCTitle | testDescriptionABC |
