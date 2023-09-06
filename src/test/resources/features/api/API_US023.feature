@alumni
Feature: [API_US023] As an administrator, I want to access the Student List through API connection.

  @get
  Scenario: [TC_01] Success Response

    * Prepare url with path parameters "api/studentList".
    * Prepare "admin" "furkan" "CorrectPassword" token
    * Get request.
    * Verify that status code 200 and message is "Success".

  @get
  Scenario: [TC_02] Failed Response

    * Prepare url with path parameters "api/studentList".
    * Prepare "admin" "furkan" "WrongPassword" token
    * Get request.
    * Verify that status code 403 and message is "failed".

  @API_US_023_TC_03
  Scenario: TC_03 Is The Expected Body same as The Response Body for Alumni Get Students.
    * Prepare url with path parameters "api/studentList".
    * Prepare "admin" "furkan" "CorrectPassword" token
    * Get request.
    * Verify response body information for Alumni.