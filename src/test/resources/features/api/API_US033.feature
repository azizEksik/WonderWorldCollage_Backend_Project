@API_US033
Feature: [API_US033] As an administrator, I want to access the Visitor List through API connection.

  Scenario: [TC_01] Valid GET request with authorization information for visitorsList
    
    * Prepare url with path parameters "api/visitorsList".
    * Prepare "admin" "zehranur" "CorrectPassword" token
    * Get request.
    * Verify that status code 200 and message is "Success".

  Scenario: [TC_02] Invalid GET request with authorization information for visitorsList

    * Prepare url with path parameters "api/visitorsList".
    * Prepare "admin" "zehranur" "WrongPassword" token
    * Get request.
    * Verify that status code 403 and message is "failed".

  @TC_03
  Scenario: TC_03 Response body content check test for visitorsList

    * Prepare url with path parameters "api/visitorsList".
    * Prepare "admin" "zehranur" "CorrectPassword" token
    * Get request.
    * Response body content check test for visitorsList