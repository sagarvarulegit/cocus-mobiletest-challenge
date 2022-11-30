Feature: Add Notes

  @sanity 
  Scenario Outline: Add Small Note
    When I add Note with "<title>" and "<description>"
    Then Verify note is added successfully with title and description

    Examples: 
      | title      | description                  |
      | testTitle2 | text: Some text Description2 |
      | testTitle1 | text: Some text Description2 |

  @sanity
  Scenario Outline: Large text file as Note
    When I add Note with "<title>" and "<description>"
    Then Verify note is added successfully with title and description

    Examples: 
      | title      | description            |
      | testTitle1 | file:largetextFile.txt |

  @getDataFromAPI=randomuser.json @sanity
  Scenario: Add Note from API
    When I add Note with title and description using API
    Then Verify note is added successfully with title and description

  Scenario: Note are Persisted after Add Image
    When I add Note with "Title_Marco" and "text: Some text Description2"
    When I click on Add Image for new note
    Then Verify note is added successfully with title and description

  
  Scenario: Verify Number of Notes
    When I add Note following notes
      | WebWorld    | Web is web of nodes |
      | Movies      | Marvel,DCU,Avatar   |
      | ThingstoBuy | Food,Clothes,House  |
    Then Verify Note count is "3"


  Scenario: Notes are saved after Restarting App
    Given I am at dashboard
    When I add Note following notes
      | WebWorld    | Web is web of nodes |
      | Movies      | Marvel,DCU,Avatar   |
      | ThingstoBuy | Food,Clothes,House  |
    And I restart app
    Then Verify Note count is "3"


  Scenario: Verify Blank Note Cannot be added (hit enter)
    When I add blank Note
    Then Verify Blank note is note saved
