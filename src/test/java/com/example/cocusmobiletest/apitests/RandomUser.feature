Feature: Random Users

  Background: 
    * url 'https://randomuser.me'

  @get-user
  Scenario: Get Random User data
    Given path 'api'
    When method get
    Then status 200
    * def nameschema =
      """
          {
                  "title": "#string",
                  "first": "#string",
                  "last": "#string"
                  
          }
      """
    * match response.results[0].name == nameschema
    * def regschema =
      """
          {
                  "date": "#string",
                  "age": "#number"
          }
      """
    * match response.results[0].registered == regschema
    * string json = response
    * def Util = Java.type('com.example.cocusmobiletest.utils.TestUtils')
    * def SaveResponse = Util.writeToJSONFile(json,'randomuser.json')
