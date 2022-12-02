@sanity @regression
Feature: Add Notes

  @sanity
  Scenario Outline: Add Small Note
    When I add Note with "<title>" and "<description>"
    Then Verify note is added successfully with title and description

    Examples: 
      | title      | description                  |
      | testTitle1 | text: Some text Description1 |
      | testTitle2 | text: Some text Description2 |

  @sanity
  Scenario: Note with Title only
    When I add Note with "Some Title" and "text:"
    Then Verify note title is "Some Title"
    And Verify note description is blank

  @sanity
  Scenario: Note with Description only
    When I add Note with "" and "text:Some Description"
    Then Verify note title is blank
    And Verify note description is "Some Description"

  @sanity
  Scenario Outline: Large text file as Note
    When I add Note with "<title>" and "<description>"
    Then Verify note is added successfully with title and description

    Examples: 
      | title      | description            |
      | testTitle3 | file:largetextFile.txt |

  @getDataFromAPI=randomuser.json @sanity
  Scenario: Add Note from API
    When I add Note with title and description using API
    Then Verify note is added successfully with title and description

  @sanity
  Scenario: Note are Persisted after Add Image
    When I add Note with "Title_Marco" and "text: Some text Description2"
    When I click on Add Image for new note
    Then Verify note is added successfully with title and description

  @sanity
  Scenario: Verify Number of Notes Count
    When I add Note following notes
      | WebWorld    | Web is web of nodes |
      | Movies      | Marvel,DCU,Avatar   |
      | ThingstoBuy | Food,Clothes,House  |
    Then Verify Note count is "3"

  @sanity
  Scenario: Notes are saved after Restarting App
    Given I am at dashboard
    When I add Note following notes
      | City      | Mumbai,NewYork, London |
      | Countries | India,Australia,Kenya  |
      | Planets   | Earth,Jupiter,Saturn   |
    And I restart app
    Then Verify Note count is "3"

  @sanity
  Scenario: Verify Blank Note Cannot be added
    When I add blank Note
    Then Verify Blank note is note saved
