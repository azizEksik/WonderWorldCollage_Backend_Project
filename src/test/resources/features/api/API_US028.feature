@books
Feature: [API_US028] As an administrator, I want to access the Books List through API connection.


  Scenario: [TC_01] Valid GET request with authorization information for booksList
    * Prepare url with path parameters "api/booksList".
    * Prepare "admin" "berke" "CorrectPassword" token
    * Get request.
    * Verify that status code 200 and message is "Success".



  Scenario: [TC_02] Invalid GET request with authorization information for booksList
    * Prepare url with path parameters "api/booksList".
    * Prepare "admin" "berke" "WrongPassword" token
    * Get request.
    * Verify that status code 403 and message is "failed".


  Scenario: [TC_03] Response body content check test for booksList
    * Prepare url with path parameters "api/booksList".
    * Prepare "admin" "berke" "CorrectPassword" token
    * Get request.
    * Response body content check test for bookList