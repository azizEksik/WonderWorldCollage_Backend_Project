@bookAndAlumni
Feature: [API_US030] As an administrator, I want to create a new Books record through API connection.

  Scenario: [TC_01] Valid POST request with auth information for booksAdd
    * Prepare url with path parameters "api/booksAdd".
    * Prepare "admin" "berke" "CorrectPassword" token
    * POST Request for booksAdd
    * Verify that status code 200 and message is "Success" for POST Request


  Scenario: [TC_02] Invalid POST request with auth information for booksAdd
    * Prepare url with path parameters "api/booksAdd".
    * Prepare "admin" "berke" "WrongPassword" token
    * POST Request for booksAdd
    * Verify that status code 403 and message is "failed" for POST Request


  Scenario: [TC_03] Response body content check test for booksAdd
    * Prepare url with path parameters "api/booksAdd".
    * Prepare "admin" "berke" "CorrectPassword" token
    * POST Request for booksAdd
    * Response body content check test for bookAdd