
  Feature: [API_US007] As an administrator, I want to access the Alumni Events List through API connection.

    @API_US007_TC01
    Scenario: [TC_01] Valid GET request with authorization information for alumniEventsList
      * Prepare url with path parameters "api/alumniEventsList ".
      * Prepare "admin" "hilal" "CorrectPassword" token
      * Get request.
      * Verify that status code 200 and message is "Success".

    @API_US007_TC02
    Scenario: [TC_02] Invalid GET request with authorization information for alumniEventsList
      * Prepare url with path parameters "api/alumniEventsList ".
      * Prepare "admin" "hilal" "WrongPassword" token
      * Get request.
      * Verify that status code 403 and message is "failed".

     @API_US007_TC03
     Scenario: [TC_03] Response body content check test for alumniEventsList
       * Prepare url with path parameters "api/alumniEventsList ".
       * Prepare "admin" "hilal" "CorrectPassword" token
       * Get request.
       * Response body content check test for Alumni Events List

