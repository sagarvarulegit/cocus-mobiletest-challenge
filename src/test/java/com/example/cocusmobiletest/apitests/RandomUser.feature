Feature: Random Users

  Background: 
    * url 'https://randomuser.me'

  @get-user
  Scenario: Get Random User data
    Given path 'api'
    When method get
    Then status 200
    * string json = response
    * def Util = Java.type('com.example.cocusmobiletest.utils.TestUtils')
    * def SaveResponse = Util.writeToJSONFile(json,'randomuser.json')
