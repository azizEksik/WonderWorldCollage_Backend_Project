@alumni
Feature: API_US_024 As an administrator, I want to access the Alumni information of an alumnus with a given ID through API connection.

  @API_US_024_TC_01
  Scenario: TC_01 Success Response

    * Prepare url with path parameters "api/alumniId".
    * Prepare "admin" "furkan" "CorrectPassword" token
    * POST Request for alumniid.
    * Verify that status code 200 and message is "Success" for POST Request


  @API_US_024_TC_02
  Scenario: TC_02 Failed Response

    * Prepare url with path parameters "api/alumniId".
    * Prepare "admin" "furkan" "WrongPassword" token
    * POST Request for alumniid.
    * Verify that status code 403 and message is "failed" for POST Request


  @API_US_024_TC_03
  Scenario: TC_03 Is The Expected Body same as The Response Body for Alumni Get Students.

    * Prepare url with path parameters "api/alumniId".
    * Prepare "admin" "furkan" "CorrectPassword" token
    * POST Request for alumniid.
    * Verify response body information for AlumniId.
