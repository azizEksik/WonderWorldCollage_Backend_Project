@bookAndAlumni
Feature: [API_US031] As an administrator, I want to update the registered Books information in the system through API connection.


  Scenario: [TC_01] Valid PATCH request with auth information for booksUpdate
    * Prepare url with path parameters "api/booksUpdate".
    * Prepare "admin" "berke" "CorrectPassword" token
    * PATCH Request for booksUpdate
    * Verify that status code 200 and message is "Success" for PATCH Request


  Scenario: [TC_02] Invalid PATCH request with auth information for booksUpdate
    * Prepare url with path parameters "api/booksUpdate".
    * Prepare "admin" "berke" "WrongPassword" token
    * PATCH Request for booksUpdate
    * Verify that status code 403 and message is "failed" for PATCH Request


  Scenario: [TC_03] To validate book update, POST to booksId with updateId to check updated details
    * Prepare url with path parameters "api/booksUpdate".
    * Prepare "admin" "berke" "CorrectPassword" token
    * PATCH Request for booksUpdate
    * Verify response updateId matches PATCH request id in booksUpdate.


  Scenario: [TC_04] Response body content check test for booksUpdate
    * Prepare url with path parameters "api/booksUpdate".
    * Prepare "admin" "berke" "CorrectPassword" token
    * PATCH Request for booksUpdate
    * Response body content check test for bookUpdate

