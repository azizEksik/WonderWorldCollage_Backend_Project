Feature: API_US033 As an administrator, I want to access the Visitor information of a visitor with a given ID through API connection.

  Scenario: [TC_01] Valid POST request with auth information for visitorsId

    * Prepare url with path parameters "api/visitorsId".
    * Prepare "admin" "zehranur" "CorrectPassword" token
    * POST Request for visitorsId
    * Verify that status code 200 and message is "Success" for POST Request


  Scenario: [TC_02] Invalid POST request with auth information for visitorsId

    * Prepare url with path parameters "api/visitorsId".
    * Prepare "admin" "zehranur" "WrongPassword" token
    * POST Request for visitorsId
    * Verify that status code 403 and message is "failed" for POST Request

  @zera
  Scenario: [TC_03] Response body content check test for visitorsId

    * Prepare url with path parameters "api/visitorsId".
    * Prepare "admin" "zehranur" "CorrectPassword" token
    * POST Request for visitorsId
    * Response body content check test for visitorsId