@bookAndAlumni
Feature: [API_US032] As an administrator, I want to be able to delete a Books record from the system through API connection.


  Scenario: [TC_01] Valid DELETE request with authorization information for booksDelete
    * Prepare url with path parameters "api/booksDelete".
    * Prepare "admin" "berke" "CorrectPassword" token
    * DELETE Request for booksDelete
    * Verify that status code 200 and message is "Success" for DELETE Request


  Scenario: [TC_02] Invalid DELETE request with auth information for booksDelete
    * Prepare url with path parameters "api/booksDelete".
    * Prepare "admin" "berke" "WrongPassword" token
    * DELETE Request for booksDelete
    * Verify that status code 403 and message is "failed" for DELETE Request


  Scenario: [TC_03] To validate book delete, POST to booksId with DeletedId to check updated details
    * Prepare url with path parameters "api/booksDelete".
    * Prepare "admin" "berke" "CorrectPassword" token
    * DELETE Request for booksDelete
    * Verify response updateId matches DELETE request id in booksDelete


  Scenario: [TC_04] Response body content check test for booksDelete
    * Prepare url with path parameters "api/booksDelete".
    * Prepare "admin" "berke" "CorrectPassword" token
    * DELETE Request for booksDelete
    * Response body content check test for booksDelete