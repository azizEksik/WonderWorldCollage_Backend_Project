@books
Feature: [API_US029] As an administrator, I want to access the book information of a book with a given ID through API connection.


  Scenario: [TC_01] Valid POST request with auth information for booksId
    * Prepare url with path parameters "api/booksId".
    * Prepare "admin" "berke" "CorrectPassword" token
    * POST Request for booksId
    * Verify that status code 200 and message is "Success" for POST Request


  Scenario: [TC_02] Invalid POST request with auth information for booksId
    * Prepare url with path parameters "api/booksId".
    * Prepare "admin" "berke" "WrongPassword" token
    * POST Request for booksId
    * Verify that status code 403 and message is "failed" for POST Request


  Scenario: [TC_03] Response body content check test for booksId
    * Prepare url with path parameters "api/booksId".
    * Prepare "admin" "berke" "CorrectPassword" token
    * POST Request for booksId
    * Response body content check test for bookId