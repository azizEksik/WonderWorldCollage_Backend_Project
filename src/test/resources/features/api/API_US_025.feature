@bookAndAlumni
Feature: API_US_025 As an administrator, I want to create a new Alumni record through API connection.


  Scenario: TC_01 Success Response

    * Prepare url with path parameters "api/alumniAdd".
    * Prepare "admin" "furkan" "CorrectPassword" token
    * POST Request for alumniAdd.
    * Verify that status code 200 and message is "Success" for POST Request



  Scenario: TC_02 Failed Response

    * Prepare url with path parameters "api/alumniAdd".
    * Prepare "admin" "furkan" "WrongPassword" token
    * POST Request for alumniAdd.
    * Verify that status code 403 and message is "failed" for POST Request



  Scenario: TC_03 The new alumni record should be created on the API, and this can be verified by sending the addId
            from the responsebody to the api/alumniId endpoint in a POST body to retrieve the newly created alumni record.

    * Prepare url with path parameters "api/alumniId".
    * Prepare "admin" "furkan" "CorrectPassword" token
    * POST Request for alumniAdd in alumniId.
    * Verify that status code 200 and message is "Success" for POST Request