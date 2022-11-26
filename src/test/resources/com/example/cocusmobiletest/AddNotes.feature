Feature: Add Notes

  Scenario Outline: Add Note
    When I add Note with "<title>" and "<description>"
    Then Verify note is added successfully with "<title>" and "<description>"

    Examples: 
      | title        | description        |
      | testTitleABC | testDescriptionABC |
      | testTitleXYZ | testDescriptionXYZ |

  @getDataFromAPI=randomuser.json @wip
  Scenario: Add Note from API
    When I add Note with title and description
    Then Verify note is added successfully with title and description

