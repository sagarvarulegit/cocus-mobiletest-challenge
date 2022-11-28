Feature: Add Notes

  @sanity
  Scenario Outline: Add Note
    When I add Note with "<title>" and "<description>"
    Then Verify note is added successfully with title and description

    Examples: 
      | title      | description                  |
      | testTitle1 | file:largetextFile.txt       |
      | testTitle2 | text: Some text Description2 |
      | testTitle1 | text: Some text Description2 |

  @getDataFromAPI=randomuser.json @sanity
  Scenario: Add Note from API
    When I add Note with title and description
    Then Verify note is added successfully with title and description

  Scenario: Note are Persisted after Add Image
    When I add Note with "Title_Marco" and "text: Some text Description2"
    When I click on Add Image for new note
    Then Verify note is added successfully with title and description

  @wip
  Scenario: Verify Number of Notes
    When I add Note following notes
      | title      | description                  |
      | testTitle1 | file:largetextFile.txt       |
      | testTitle2 | text: Some text Description2 |
      | testTitle1 | text: Some text Description2 |
    Then Verify Note count is "3"

  Scenario: Verify Note Title Max length
