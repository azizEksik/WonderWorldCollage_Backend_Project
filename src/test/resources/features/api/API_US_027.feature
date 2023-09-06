@bookAndAlumni
Feature: API_US_027 As an administrator, I want to be able to delete an Alumni record from the system through API connection.

  Scenario: TC_01 Success Response

    * Prepare url with path parameters "api/alumniDelete".
    * Prepare "admin" "furkan" "CorrectPassword" token
    * Delete request for Alumni delete.
    * Verify that status code 200 and message is "Success" for DELETE Request



  Scenario: TC_02 Failed Response

    * Prepare url with path parameters "api/alumniDelete".
    * Prepare "admin" "furkan" "WrongPassword" token
    * Delete request for Alumni delete.
    * Verify that status code 403 and message is "failed" for DELETE Request


  Scenario: TC_03 The DeletedId in the response body should match the id sent in the DELETE request body
            to the api/alumniDelete endpoint.

    * Prepare url with path parameters "api/alumniDelete".
    * Prepare "admin" "furkan" "CorrectPassword" token
    * Delete request for Alumni delete.
    * Verify that status code 200 and message is "Success" for DELETE Request


  Scenario: TC_04 The API should successfully delete the desired Alumni record, and this can be verified
            by sending the DeletedId from the response body to the api/alumniId endpoint in a POST body to check
            if the record no longer exists.

    * Prepare url with path parameters "api/alumniId".
    * Prepare "admin" "furkan" "CorrectPassword" token
    * POST Request for alumniDelete.
    * Verify that status code 403 and message is "failed" for DELETE Request
